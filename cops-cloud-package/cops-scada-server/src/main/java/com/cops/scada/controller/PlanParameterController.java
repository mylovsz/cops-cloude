package com.cops.scada.controller;

import com.cops.scada.entity.Plan;
import com.cops.scada.entity.VO.PlanParameterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.PlanParameter;
import com.cops.scada.service.PlanParameterService;
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
 * 工单统计参数  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-03-21
 */
@Controller
@RequestMapping("/planParameter")
public class PlanParameterController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlanParameterController.class);

    @Autowired
    private PlanParameterService planParameterService;

    @RequiresPermissions("scada:planParameter:list")
    @GetMapping("list")
    @SysLog("跳转工单统计参数列表")
    public String list(Model model){
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        return "/planParameter/list";
    }

    @RequiresPermissions("scada:planParameter:list")
    @PostMapping("list")
    @ResponseBody
    @SysLog("请求工单统计参数列表数据")
    public LayerData<PlanParameterVO> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                           @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                           ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<PlanParameterVO> layerData = new LayerData<>();
        EntityWrapper<PlanParameterVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan_parameter.del_flag",false);
        if(!map.isEmpty()){
            String planId = (String) map.get("planId");
            if(StringUtils.isNotBlank(planId)) {
                wrapper.eq("plan.id",planId);
            }else{
                map.remove("planId");
            }
            String label = (String) map.get("label");
            if(StringUtils.isNotBlank(label)) {
                wrapper.eq("plan_parameter.label",label);
            }else{
                map.remove("label");
            }

        }
        Page<PlanParameterVO> pageData = planParameterService.getPlanParameterVO(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @RequiresPermissions("scada:planParameter:add")
    @GetMapping("add")
    @SysLog("跳转新增工单统计参数页面")
    public String add(Model model){
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        return "/planParameter/add";
    }

    @RequiresPermissions("scada:planParameter:add")
    @PostMapping("add")
    @SysLog("保存新增工单统计参数数据")
    @ResponseBody
    public RestResponse add(PlanParameter planParameter){
        if(planParameter.getPlanId() == null){
            return RestResponse.failure("生产计划不能为空");
        }
        if(planParameter.getLabel() == null){
            return RestResponse.failure("名称不能为空");
        }
        planParameterService.insert(planParameter);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑工单统计参数页面")
    public String edit(Long id,Model model){
        PlanParameter planParameter = planParameterService.selectById(id);
        model.addAttribute("planParameter",planParameter);
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        return "/planParameter/edit";
    }

    @RequiresPermissions("scada:planParameter:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑工单统计参数数据")
    public RestResponse edit(PlanParameter planParameter){
        if(null == planParameter.getId() || 0 == planParameter.getId()){
            return RestResponse.failure("ID不能为空");
        }
        if(planParameter.getPlanId() == null){
            return RestResponse.failure("生产计划不能为空");
        }
        if(planParameter.getLabel() == null){
            return RestResponse.failure("名称不能为空");
        }
        planParameterService.updateById(planParameter);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:planParameter:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除工单统计参数数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        PlanParameter planParameter = planParameterService.selectById(id);
        planParameter.setDelFlag(true);
        planParameterService.updateById(planParameter);
        return RestResponse.success();
    }

}