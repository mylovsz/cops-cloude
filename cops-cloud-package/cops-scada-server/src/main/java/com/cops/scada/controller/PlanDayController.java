package com.cops.scada.controller;

import com.cops.library.until.DateConvert;
import com.cops.scada.entity.*;
import com.cops.scada.entity.VO.JobVO;
import com.cops.scada.entity.VO.PlanDayVO;
import com.cops.scada.entity.VO.QualityExaminesVO;
import com.cops.scada.entity.VO.statistic.TotalWorkDetailVO;
import com.cops.scada.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.annotation.SysLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cops.scada.util.LayerData;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import com.xiaoleilu.hutool.date.DateUtil;
import java.util.Date;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.util.RestResponse;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.cops.scada.annotation.SysLog;
import com.cops.scada.base.BaseController;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;
import com.cops.scada.base.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
/**
 * <p>
 * 日计划统计  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@Controller
@RequestMapping("/planDay")
public class PlanDayController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlanDayController.class);

    @Autowired
    private PlanDayService planDayService;

    @Autowired
    private JobService jobService;

    @Autowired
    private RepairService repairService;

    @Autowired
    private WorkDetailService workDetailService;

    @Autowired
    private ProduceService produceService;

    @Autowired
    private ExaminesAService examinesAService;

    @Autowired
    private ExaminesBService examinesBService;

    @Autowired
    private ExaminesCService examinesCService;

    @Autowired
    private ExaminesDService examinesDService;

    @Autowired
    private ExaminesEService examinesEService;

    @Autowired
    private ExaminesFService examinesFService;

    // start V3
    @RequiresPermissions("scada:planDay:list")
    @GetMapping("listV3")
    @SysLog("跳转日计划统计列表")
    public String listV3(Model model){
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        return "/planDay/listV3";
    }
    @RequiresPermissions("scada:planDay:add")
    @GetMapping("addV3")
    @SysLog("跳转新增日计划统计页面")
    public String addV3(Model model){
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        return "/planDay/addV3";
    }
    @PostMapping("getPlanAggregateNum")
    @ResponseBody
    public RestResponse getPlanAggregateNum(@RequestParam(value = "planId",required = false)Long planId,
                                            @RequestParam(value = "jobId",required = false)Long jobId){
        if(null == planId || null == jobId){
            return RestResponse.failure("计划ID不能为空");
        }
        Integer num = planDayService.getPlanForMakeNum(planId, jobId);
        return RestResponse.success().setData(num == null?0:num);
    }
    // end V3

    @GetMapping("board")
    @SysLog("系统看板")
    public String board(Model model){

        Dict dict = dictService.selectById(393);
        model.addAttribute("time",dict);
        return "/planDay/board";
    }

    @GetMapping("board2")
    @SysLog("系统看板")
    public String board2(){

        return "/planDay/board2";
    }

    @RequiresPermissions("scada:planDay:list")
    @GetMapping("list")
    @SysLog("跳转日计划统计列表")
    public String list(Model model){
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        return "/planDay/list";
    }

    @RequiresPermissions("scada:planDay:listReport")
    @GetMapping("listReport")
    @SysLog("跳转日计划统计列表")
    public String listReport(Model model){
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        List<Product> productList = productService.selectAll();
        model.addAttribute("productSNList", productList);
        List<Job> jobList = jobService.getJobTemplate();
        model.addAttribute("jobList", jobList);
        return "/planDay/listReport";
    }


    @GetMapping("board3")
    @SysLog("跳转日计划统计列表")
    public String listReport3(Model model){
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        List<Product> productList = productService.selectAll();
        model.addAttribute("productSNList", productList);
        List<Job> jobList = jobService.getJobTemplate();
        model.addAttribute("jobList", jobList);
        return "/planDay/board3";
    }

    @PostMapping("list")
    @ResponseBody
    public LayerData<PlanDayVO> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                     @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                     ServletRequest request){
        Boolean isIncludeRepair = false;
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<PlanDayVO> layerData = new LayerData<>();
        EntityWrapper<PlanDayVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan_day.del_flag",false);
        wrapper.orderBy("master_plan_sn");
        wrapper.orderBy("plan_day.manufacture_date", false)
                .orderBy("job.name")
                .orderBy("type")
                .orderBy("plan.sn");
        if(!map.isEmpty()){
            String manufactureDate = (String) map.get("manufactureDate");
            if(StringUtils.isNotBlank(manufactureDate)) {
                Date begin = DateUtil.parse(manufactureDate);
                wrapper.between("plan_day.manufacture_date",
                        DateConvert.getStartTime(begin), DateConvert.getEndTime(begin));
            }else{
                map.remove("manufactureDate");
            }

            String beginManufactureDate = (String) map.get("beginManufactureDate");
            String endManufactureDate = (String) map.get("endManufactureDate");
            if(StringUtils.isNotBlank(beginManufactureDate)) {
                Date begin = DateUtil.parse(beginManufactureDate);
                wrapper.ge("plan_day.manufacture_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginManufactureDate");
            }
            if(StringUtils.isNotBlank(endManufactureDate)) {
                Date end = DateUtil.parse(endManufactureDate);
                wrapper.le("plan_day.manufacture_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endManufactureDate");
            }

            String state = (String) map.get("state");
            if(StringUtils.isNotBlank(state)) {
                wrapper.eq("plan_day.state",state);
            }else{
                map.remove("state");
            }

            String type = (String) map.get("type");
            if(StringUtils.isNotBlank(type)) {
                wrapper.eq("plan_day.type",type);
            }else{
                map.remove("type");
            }

            String jobSn = (String) map.get("jobSn");
            if(StringUtils.isNotBlank(jobSn)) {
                wrapper.eq("job.sn",jobSn);
            }else{
                map.remove("jobSn");
            }

            String productSN = (String) map.get("productSN");
            if(StringUtils.isNotBlank(productSN)) {
                wrapper.like("product.sn",productSN.trim());
            }else{
                map.remove("productSN");
            }

            String nostate = (String) map.get("nostate");
            if(StringUtils.isNotBlank(nostate)) {
                wrapper.ne("plan_day.state",nostate);
            }else{
                map.remove("nostate");
            }

            // master
            String masterProductName = (String) map.get("masterProductName");
            if(StringUtils.isNotBlank(masterProductName)) {
                wrapper.eq("master_product.name",masterProductName);
            }else{
                map.remove("masterProductName");
            }
            String masterProductSn = (String) map.get("masterProductSn");
            if(StringUtils.isNotBlank(masterProductSn)) {
                wrapper.eq("master_product.sn",masterProductSn);
            }else{
                map.remove("masterProductSn");
            }
            String masterPlanSn = (String) map.get("masterPlanSn");
            if(StringUtils.isNotBlank(masterPlanSn)) {
                wrapper.eq("master_plan.sn",masterPlanSn);
            }else{
                map.remove("masterPlanSn");
            }
            String masterPlanNcId = (String) map.get("masterPlanNcId");
            if(StringUtils.isNotBlank(masterPlanNcId)) {
                wrapper.eq("master_plan.nc_id",masterPlanNcId);
            }else{
                map.remove("masterPlanNcId");
            }
            // end master

            String planType = (String) map.get("planType");
            if(StringUtils.isNotBlank(planType)) {
                wrapper.eq("plan.type",planType);
            }else{
                map.remove("planType");
            }

            String planId = (String) map.get("planId");
            if(StringUtils.isNotBlank(planId)) {
                wrapper.eq("plan.id",planId);
            }else{
                map.remove("planId");
            }

            String planNcId = (String) map.get("planNcId");
            if(StringUtils.isNotBlank(planNcId)) {
                wrapper.eq("plan.nc_id",planNcId);
            }else{
                map.remove("planNcId");
            }

            String includeRepair = (String) map.get("includeRepair");
            if(StringUtils.isNotBlank(includeRepair)) {
                isIncludeRepair = true;
            }else{
                map.remove("includeRepari");
            }
        }
        Page<PlanDayVO> pageData = planDayService.getPagePlanDayVO(new Page<>(page,limit),wrapper);
        List<PlanDayVO> result = pageData.getRecords();
        if(isIncludeRepair){
            for (PlanDayVO p :
                    result) {
                Integer n = repairService.getRepairForCountBy(p.getPlanId(), p.getManufactureDate(),
                        p.getJobSn());
                if(n == null) n = 0;
                p.setSendRepairNum(n);
            }
        }

        layerData.setData(result);
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @RequiresPermissions("scada:planDay:add")
    @GetMapping("add")
    @SysLog("跳转新增日计划统计页面")
    public String add(Model model){
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        return "/planDay/add";
    }

    @RequiresPermissions("scada:planDay:add")
    @PostMapping("add")
    @SysLog("保存新增日计划统计数据")
    @ResponseBody
    public RestResponse add(PlanDay planDay){
        // 待产
        planDay.setState(-1);
        planDayService.insert(planDay);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:planDay:edit")
    @GetMapping("edit")
    @SysLog("跳转编辑日计划统计页面")
    public String edit(Long id,Model model){
        PlanDayVO planDay = planDayService.getPlanDay(id);
        model.addAttribute("planDay",planDay);
        return "/planDay/edit";
    }

    @RequiresPermissions("scada:planDay:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑日计划统计数据")
    public RestResponse edit(PlanDay planDay){
        if(null == planDay.getId() || 0 == planDay.getId()){
            return RestResponse.failure("ID不能为空");
        }

        PlanDay p = planDayService.updatePlanDay(planDay);

        // 防止jobid发生改变
        // 因为planDay可能存在不传输job_id的问题
        JobVO job = jobService.getJobVOById(p.getJobId());
        if(job.getSn().equals("JOB0001")
            || job.getSn().equals("JOB0004")){
            planDay.setNumSuccess(planDay.getManufactureNum());
            planDay.setNum(planDay.getManufactureNum());
            planDayService.updatePlanDay(planDay);
        }

        return RestResponse.success();
    }

    @RequiresPermissions("scada:planDay:reportWork")
    @GetMapping("reportWork")
    @SysLog("跳转编辑日计划统计页面")
    public String reportWork(Long id,Model model){
        PlanDayVO planDay = planDayService.getPlanDay(id);
        model.addAttribute("planDay",planDay);

        // start 工时相关信息
        TotalWorkDetailVO totalWorkDetailVO = workDetailService.getTotalWorkDetailVO(planDay.getId());
        model.addAttribute("totalWorkDetailVO", totalWorkDetailVO);
        // end 工时相关信息

        return "/planDay/reportWork";
    }

    @RequiresPermissions("scada:planDay:reportWork")
    @PostMapping("reportWork")
    @ResponseBody
    public RestResponse reportWork(PlanDay planDay){
        if(null == planDay.getId() || 0 == planDay.getId()){
            return RestResponse.failure("ID不能为空");
        }

        PlanDay p = planDayService.updatePlanDay(planDay);

        // 防止jobid发生改变
        // 因为planDay可能存在不传输job_id的问题
        JobVO job = jobService.getJobVOById(p.getJobId());
        if(job.getSn().equals("JOB0001")
                || job.getSn().equals("JOB0004")){
            planDay.setNumSuccess(planDay.getManufactureNum());
            planDay.setNum(planDay.getManufactureNum());
            planDayService.updatePlanDay(planDay);
        }

        return RestResponse.success();
    }

    @RequiresPermissions("scada:planDay:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除日计划统计数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        PlanDay planDay = planDayService.selectById(id);
        planDay.setDelFlag(true);
        planDayService.updateById(planDay);
        return RestResponse.success();
    }

}