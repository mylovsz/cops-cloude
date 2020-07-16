package com.cops.scada.controller;

import com.cops.scada.entity.VO.StatisticsDayVO;
import com.cops.scada.service.StatisticsDayService;
import com.cops.scada.util.RestResponse;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 每天邮件数据统计用，现在废弃，去定时任务找这个
 */
@Controller
@RequestMapping("/StatisticsDay")
public class StatisticsDayController {
    @Autowired
    private StatisticsDayService statisticsDayService;

    @GetMapping("A")
    @ResponseBody
    public RestResponse collect(){
//        // Examines a相关
//        //Date old = new Date(d.getTime()-(long)29*24*60*60*1000);
//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.DATE, -1);
//        c.set(Calendar.AM_PM, 0);
//        c.set(Calendar.HOUR_OF_DAY, 0);
//        c.set(Calendar.MINUTE, 0);
//        c.set(Calendar.SECOND, 0);
//        c.set(Calendar.MILLISECOND, 0);
//        Date old = c.getTime();
//
//        Calendar d = Calendar.getInstance();
//        d.add(Calendar.DATE, -0);
//        d.set(Calendar.AM_PM, 0);
//        d.set(Calendar.HOUR_OF_DAY, 0);
//        d.set(Calendar.MINUTE, 0);
//        d.set(Calendar.SECOND, 0);
//        d.set(Calendar.MILLISECOND, 0);
//        Date t2 = d.getTime();
//
        RestResponse rr = RestResponse.success();
//
//        //测试数据
//        List<StatisticsDayVO> listOK;//全部
//        List<StatisticsDayVO> listAF;//异常
//        List<StatisticsDayVO> listExamines =new ArrayList<>();
//        listOK = statisticsDayService.getStatisticsDayCollect("scada_examines_a",old, t2,null);
//        listAF = statisticsDayService.getStatisticsDayCollect("scada_examines_a",old, t2,"!=PASS");
//        listExamines.add(StatisticsDayVOInit("初检耐压",listOK,listAF));
//
//        listOK = statisticsDayService.getStatisticsDayCollect("scada_examines_b",old, t2,null);
//        listAF = statisticsDayService.getStatisticsDayCollect("scada_examines_b",old, t2,"!=PASS");
//        listExamines.add(StatisticsDayVOInit("初检",listOK,listAF));
//
//        listOK = statisticsDayService.getStatisticsDayCollect("scada_examines_c",old, t2,null);
//        listAF = statisticsDayService.getStatisticsDayCollect("scada_examines_c",old, t2,"!=PASS");
//        listExamines.add(StatisticsDayVOInit("老化",listOK,listAF));
//
//        listOK = statisticsDayService.getStatisticsDayCollect("scada_examines_d",old, t2,null);
//        listAF = statisticsDayService.getStatisticsDayCollect("scada_examines_d",old, t2,"!=PASS");
//        listExamines.add(StatisticsDayVOInit("终检耐压",listOK,listAF));
//
//
//        listOK = statisticsDayService.getStatisticsDayCollect("scada_examines_e",old, t2,null);
//        listAF = statisticsDayService.getStatisticsDayCollect("scada_examines_e",old, t2,"!=PASS");
//        listExamines.add(StatisticsDayVOInit("点火",listOK,listAF));
//
//        listOK = statisticsDayService.getStatisticsDayCollect("scada_examines_f",old, t2,null);
//        listAF = statisticsDayService.getStatisticsDayCollect("scada_examines_f",old, t2,"!=PASS");
//        listExamines.add(StatisticsDayVOInit("终检",listOK,listAF));
//
//        rr.setAny("examines", listExamines);
//
//        //维修数据统计
//        List<StatisticsDayVO> listRepair= statisticsDayService.getStatisticsDayRepairCollect(old, t2);
//        List<StatisticsDayVO>  repair =new ArrayList<>();
//        double rCount=0;
//
//        for (int i = 0; i < listRepair.size(); i++) {
//            rCount+= Double.parseDouble((listRepair.get(i).getValue2()));
//        }
//        for (int i = 0; i < listRepair.size(); i++) {
//            StatisticsDayVO a=new StatisticsDayVO();
//            double can = Double.parseDouble(listRepair.get(i).getValue2());
//
//            a.setValue1(listRepair.get(i).getValue1());
//            a.setValue2(String.valueOf((long)rCount));
//            a.setValue3(listRepair.get(i).getValue2());
//            a.setValue4(String.format("%.3f", (can/rCount)*100)+"%");
//            repair.add(a);
//        }
//        rr.setAny("repair", repair);
//
//        List<StatisticsDayVO> listPlan= statisticsDayService.getStatisticsDayPlanCollect(old, t2);
//        rr.setAny("plans", listPlan);
        return rr;
    }

    public StatisticsDayVO StatisticsDayVOInit(String name,List<StatisticsDayVO> list1,List<StatisticsDayVO> list2)
    {
        StatisticsDayVO A=new StatisticsDayVO();
//        double ac = Double.parseDouble(list1.get(0).getValue1());//总数
//        double af = Double.parseDouble(list2.get(0).getValue1());//不良
//        double fr = af/ac*1.000;//故障率
//        A.setValue1(name);
//        A.setValue2(String.valueOf((long)ac));
//        A.setValue3(String.valueOf((long)af));
//        A.setValue4(String.format("%.3f", fr*100)+"%");
        return A;
    }
}
