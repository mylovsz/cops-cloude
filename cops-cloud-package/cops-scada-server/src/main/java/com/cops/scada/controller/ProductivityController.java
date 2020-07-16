package com.cops.scada.controller;

import com.cops.scada.annotation.SysLog;
import com.cops.scada.base.BaseController;
import com.cops.scada.entity.VO.StatisticsExaminesCollect;
import com.cops.scada.util.RestResponse;
import com.google.common.collect.Lists;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 品质日报  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-03-14
 */
@Controller
@RequestMapping("/productivity")
public class ProductivityController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QualityController.class);


    @GetMapping("report")
    @SysLog("跳转生产力报告页面")
    public String report(Model model) {
        return "/productivity/report";
    }


    @GetMapping("reportdata")
    @ResponseBody
    public RestResponse reportData(){
        RestResponse rr = RestResponse.success();
        rr.setAny("a","123");
        rr.setAny("b","456");
        return rr;
    }

}
