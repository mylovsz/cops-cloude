package com.cops.scada.util.quartz.task;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.scada.entity.BlogArticle;
import com.cops.scada.entity.VO.StatisticsDayVO;
import com.cops.scada.service.BlogArticleService;
import com.cops.scada.service.StatisticsDayService;
import com.cops.scada.util.RestResponse;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangl on 2018/1/26.
 * todo: 系统定时任务
 */
@Component("ScadaTask")
public class ScadaTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${MailTemplate.path}")
    private String mailPath;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${MailCC.CC}")
    private String[] CC;

    @Value("${MailTo.To}")
    private String[] To;


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private StatisticsDayService statisticsDayService;

    public StatisticsDayVO StatisticsDayVOInit(String name, List<StatisticsDayVO> list1, List<StatisticsDayVO> list2)
    {
        StatisticsDayVO A=new StatisticsDayVO();
        double ac = Double.parseDouble(list1.get(0).getValue1());//总数
        double af = Double.parseDouble(list2.get(0).getValue1());//不良
        double fr=0.00;
        if (ac!=0)
            fr = af/ac*1.000;//故障率

        A.setValue1(name);
        A.setValue2(String.valueOf((long)ac));
        A.setValue3(String.valueOf((long)af));
        A.setValue4(String.format("%.2f", fr*100)+"%");
        return A;
    }

    public void SendMail(String params) throws MessagingException, IOException, TemplateException {

        logger.info("每日报表发送参数："+params);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        c.set(Calendar.AM_PM, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date old = c.getTime();

        Calendar d = Calendar.getInstance();
        d.add(Calendar.DATE, -0);
        d.set(Calendar.AM_PM, 0);
        d.set(Calendar.HOUR_OF_DAY, 0);
        d.set(Calendar.MINUTE, 0);
        d.set(Calendar.SECOND, 0);
        d.set(Calendar.MILLISECOND, 0);
        Date t2 = d.getTime();

        RestResponse rr = RestResponse.success();

        //测试数据
        List<StatisticsDayVO> listOK;//全部
        List<StatisticsDayVO> listAF;//异常
        List<StatisticsDayVO> listExamines =new ArrayList<>();
        listOK = statisticsDayService.getStatisticsDayCollect("scada_examines_a",old, t2,null);
        listAF = statisticsDayService.getStatisticsDayCollect("scada_examines_a",old, t2,"!=PASS");
        listExamines.add(StatisticsDayVOInit("初检耐压",listOK,listAF));

        listOK = statisticsDayService.getStatisticsDayCollect("scada_examines_b",old, t2,null);
        listAF = statisticsDayService.getStatisticsDayCollect("scada_examines_b",old, t2,"!=PASS");
        listExamines.add(StatisticsDayVOInit("初检",listOK,listAF));

        listOK = statisticsDayService.getStatisticsDayCollect("scada_examines_c",old, t2,null);
        listAF = statisticsDayService.getStatisticsDayCollect("scada_examines_c",old, t2,"!=PASS");
        listExamines.add(StatisticsDayVOInit("老化",listOK,listAF));

        listOK = statisticsDayService.getStatisticsDayCollect("scada_examines_d",old, t2,null);
        listAF = statisticsDayService.getStatisticsDayCollect("scada_examines_d",old, t2,"!=PASS");
        listExamines.add(StatisticsDayVOInit("终检耐压",listOK,listAF));

        listOK = statisticsDayService.getStatisticsDayCollect("scada_examines_e",old, t2,null);
        listAF = statisticsDayService.getStatisticsDayCollect("scada_examines_e",old, t2,"!=PASS");
        listExamines.add(StatisticsDayVOInit("点火",listOK,listAF));

        listOK = statisticsDayService.getStatisticsDayCollect("scada_examines_f",old, t2,null);
        listAF = statisticsDayService.getStatisticsDayCollect("scada_examines_f",old, t2,"!=PASS");
        listExamines.add(StatisticsDayVOInit("终检",listOK,listAF));

        //维修数据统计
        List<StatisticsDayVO> listRepair= statisticsDayService.getStatisticsDayRepairCollect(old, t2);
        List<StatisticsDayVO>  repair =new ArrayList<>();
        double rCount=0;

        for (int i = 0; i < listRepair.size(); i++) {
            rCount+= Double.parseDouble((listRepair.get(i).getValue2()));
        }
        for (int i = 0; i < listRepair.size(); i++) {
            StatisticsDayVO a=new StatisticsDayVO();
            double can = Double.parseDouble(listRepair.get(i).getValue2());

            a.setValue1(listRepair.get(i).getValue1());
            a.setValue2(String.valueOf((long)rCount));
            a.setValue3(listRepair.get(i).getValue2());
            if ( rCount!=0)
                a.setValue4(String.format("%.2f", (can/rCount)*100)+"%");
            else
                a.setValue4(String.format("%.2f", 0*100)+"%");
            repair.add(a);
        }


        Calendar c2 = Calendar.getInstance();
        c2.add(Calendar.DATE, -7);
        c2.set(Calendar.AM_PM, 0);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        Date old1 = c2.getTime();

        List<StatisticsDayVO> listPlan= statisticsDayService.getStatisticsDayPlanCollect(old1, t2);

        String day = new SimpleDateFormat("yyyy-MM-dd").format(old);
        Map<String, Object> model = new HashMap<>();
        model.put("day",day);
        model.put("examines",listExamines);
        model.put("repair", repair);

        if (listPlan.size()>0)
        {
            model.put("plans", listPlan);
        }
        else
        {
            model.put("plans", null);
        }


        //model.put("base", "http://192.168.1.244:8081");
        long startTime = System.currentTimeMillis();
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File(mailPath));
        Template template = configuration.getTemplate("mailQualityDay.ftl","UTF-8");
        String emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        long endTime = System.currentTimeMillis();
        System.out.println("FreeMarker:\t"+(endTime-startTime));
        System.out.println(emailContent);


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(username);//"ermao.wang@lumlux.com");

//        helper.setTo("2507988330@qq.com");
//        helper.setCc("ermao.wang@lumlux.com");
        helper.setCc(CC);
        helper.setTo(To);
        helper.setSubject(day + " 生产日报");
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    /**
     * 发送邮件
     * @param params
     */
    public void  sendMailQuality(String params){
//        Map<String, Object> model = new HashMap<>();
//        model.put("info", "To ALL，下面是04年10的生产状况");
//        Template template n
    }


}
