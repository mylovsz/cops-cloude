package com.cops.scada.controller;

import com.cops.scada.entity.Plan;
import com.cops.scada.entity.VO.JobVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.Job;
import com.cops.scada.service.JobService;
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
 * 作业（工艺）管理  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@Controller
@RequestMapping("/job")
public class JobController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobService jobService;

    // API接口
    @PostMapping("getJobVOByPlanId")
    @ResponseBody
    @SysLog("请求作业（工艺）管理列表数据 通过计划ID")
    public RestResponse getJobVOByPlanId(@RequestParam Long planId){
        List<JobVO> jobVOList = jobService.getJobVOByPlanId(planId);
        return RestResponse.success().setData(jobVOList);
    }
    // End API接口

    @GetMapping("list")
    @SysLog("跳转作业（工艺）管理列表")
    public String list(Model model){
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        return "/job/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求作业（工艺）管理列表数据")
    public LayerData<JobVO> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                 @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                 ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<JobVO> layerData = new LayerData<>();
        EntityWrapper<JobVO> wrapper = new EntityWrapper<>();
        wrapper.eq("job.del_flag",false);
        if(!map.isEmpty()){
            String identityType = (String) map.get("identityType");
            if(StringUtils.isNotBlank(identityType)) {
                wrapper.eq("job.identity_type",identityType);

                if(identityType.equals("2")){
                    // 生产计划
                    String planId = (String) map.get("planId");
                    if(StringUtils.isNotBlank(planId)) {
                        wrapper.eq("plan.id",planId);
                    }else{
                        map.remove("planId");
                    }
                }
            }else{
                map.remove("identityType");
            }

            String type = (String) map.get("type");
            if(StringUtils.isNotBlank(type)) {
                wrapper.eq("job.type",type);
            }else{
                map.remove("type");
            }

            String sn = (String) map.get("sn");
            if(StringUtils.isNotBlank(sn)) {
                wrapper.like("job.sn",sn);
            }else{
                map.remove("sn");
            }

            String name = (String) map.get("name");
            if(StringUtils.isNotBlank(name)) {
                wrapper.like("job.name",name);
            }else{
                map.remove("name");
            }

            String sopName = (String) map.get("sopName");
            if(StringUtils.isNotBlank(sopName)) {
                wrapper.like("job.sop_name",sopName);
            }else{
                map.remove("sopName");
            }

        }
        Page<JobVO> pageData = jobService.getPageJobVO(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增作业（工艺）管理页面")
    public String add(Model model){
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        return "/job/add";
    }

    @PostMapping("add")
    @SysLog("保存新增作业（工艺）管理数据")
    @ResponseBody
    public RestResponse add(Job job){
        if(StringUtils.isBlank(job.getSn())){
            return RestResponse.failure("作业编号不能为空");
        }
        if(StringUtils.isBlank(job.getName())){
            return RestResponse.failure("作业名称不能为空");
        }
        jobService.insert(job);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑作业（工艺）管理页面")
    public String edit(Long id,Model model){
        JobVO job = jobService.getJobVOById(id);
        model.addAttribute("job",job);
        return "/job/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑作业（工艺）管理数据")
    public RestResponse edit(Job job){
        if(null == job.getId() || 0 == job.getId()){
            return RestResponse.failure("ID不能为空");
        }
        if(StringUtils.isBlank(job.getSn())){
            return RestResponse.failure("作业编号不能为空");
        }
        if(StringUtils.isBlank(job.getName())){
            return RestResponse.failure("作业名称不能为空");
        }
        jobService.updateById(job);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除作业（工艺）管理数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        Job job = jobService.selectById(id);
        job.setDelFlag(true);
        jobService.updateById(job);
        return RestResponse.success();
    }

}