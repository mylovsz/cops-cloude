package com.cops.scada.util.quartz.task;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.scada.entity.BlogArticle;
import com.cops.scada.service.BlogArticleService;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangl on 2018/1/26.
 * todo: 系统定时任务
 */
@Component("systemTask")
public class SystemTask {
    private static Log log = LogFactory.get();

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private BlogArticleService blogArticleService;

    /**
     * 同步文章点击量
     * @param params
     */
    public void  synchronizationArticleView(String params){
        ValueOperations<String, Object> operations=redisTemplate.opsForValue();
        EntityWrapper<BlogArticle> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        List<BlogArticle> list = blogArticleService.selectList(wrapper);
        for (BlogArticle article : list){
            String key = "article_click_id_"+article.getId();
            if(redisTemplate.hasKey(key)){
                Integer count = (Integer)operations.get(key);
                if(count > 0){
                    article.setClick(blogArticleService.getArticleClick(article.getId()));
                    if(StringUtils.isNotBlank(params)){
                        article.setUpdateId(Long.valueOf(params));
                    }
                    blogArticleService.updateById(article);
                }
            }
        }
    }

    /**
     * 生成文章搜索索引
     */
    public void createArticleIndex(String params) {
        blogArticleService.createArticlIndex();
    }




    @Value("${MailTemplate.path}")
    private String mailPath;

    @Autowired
    private JavaMailSender javaMailSender;
    /**
     * 邮件发送
     */
    public void Email_Send()throws MessagingException, IOException, TemplateException {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("ermao.wang@lumlux.com");
        mailMessage.setTo("2507988330@qq.com");
        mailMessage.setSubject("邮件测试");
        mailMessage.setText("发送简单邮件");
        try {
            javaMailSender.send(mailMessage);
            System.out.println("发送简单邮件");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("发送简单邮件失败");
        }

//        Map<String, Object> model = new HashMap<>();
//        model.put("info", "您好，感谢您的注册，这是一封验证邮件，请点击下面的连接完成注册，感谢您的支持！");
//        long startTime = System.currentTimeMillis();
//        Configuration configuration = new Configuration();
//        configuration.setDirectoryForTemplateLoading(new File(mailPath));
//        Template template = configuration.getTemplate("mailQualityDay.ftl");
//        String emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
//        long endTime = System.currentTimeMillis();
//        System.out.println("FreeMarker:\t"+(endTime-startTime));
//        System.out.println(emailContent);
//
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom("ermao.wang@lumlux.com");
//        simpleMailMessage.setTo("ermao.wang@lumlux.com");
//        simpleMailMessage.setSubject("测试邮件");
//        simpleMailMessage.setText(emailContent);
//
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//        helper.setFrom("ermao.wang@lumlux.com");
//        helper.setTo("ermao.wang@lumlux.com");
//        helper.setSubject("测试邮件-html");
//        helper.setText(emailContent, true);
//        javaMailSender.send(mimeMessage);
    }

}
