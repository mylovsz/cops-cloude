package com.cops.scada.controller;

import com.cops.scada.entity.VO.PlanLinkSlaveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.PlanLink;
import com.cops.scada.service.PlanLinkService;
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
 * 生产计划关联  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2020-04-08
 */
@Controller
@RequestMapping("/planLink")
public class PlanLinkController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlanLinkController.class);

    @Autowired
    private PlanLinkService planLinkService;

    @PostMapping("removePlanLink")
    @ResponseBody
    @SysLog("删除生产计划关联数据")
    public RestResponse removePlanLink(Long masterPlanId, Long slavePlanId){
        PlanLinkSlaveVO planLinkSlaveVO = planLinkService.getPlanLinkSlaveVO(masterPlanId, slavePlanId);
        if(planLinkSlaveVO == null){
            return RestResponse.failure("无数据关联，无需移除");
        }
        if(planLinkService.deleteById(planLinkSlaveVO.getId())) {
            return RestResponse.success();
        }
        return RestResponse.failure("数据移除失败，请联系数据管理员！");
    }

    @PostMapping("getPlanLinkSlaveVO")
    @ResponseBody
    @SysLog("请求子计划数据")
    public LayerData<PlanLinkSlaveVO> getPlanLinkSlaveVO(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                                         @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                                         ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<PlanLinkSlaveVO> layerData = new LayerData<>();
        EntityWrapper<PlanLinkSlaveVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan_link.del_flag",false);
        wrapper.orderBy("plan_link.create_date", false);

        if(!map.isEmpty()){
            String masterPlanId = (String) map.get("masterPlanId");
            if(StringUtils.isNotBlank(masterPlanId)) {
                wrapper.eq("plan_link.master_plan_id",masterPlanId);
            }else{
                map.remove("masterPlanId");
            }
        }

        Page<PlanLinkSlaveVO> pageData = planLinkService.getPlanLinkSlaveVO(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("list")
    @SysLog("跳转生产计划关联列表")
    public String list(){
        return "/planLink/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求生产计划关联列表数据")
    public LayerData<PlanLink> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<PlanLink> layerData = new LayerData<>();
        EntityWrapper<PlanLink> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
        }
        Page<PlanLink> pageData = planLinkService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增生产计划关联页面")
    public String add(){
        return "/planLink/add";
    }

    @PostMapping("add")
    @SysLog("保存新增生产计划关联数据")
    @ResponseBody
    public RestResponse add(PlanLink planLink){
        planLinkService.insert(planLink);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑生产计划关联页面")
    public String edit(Long id,Model model){
        PlanLink planLink = planLinkService.selectById(id);
        model.addAttribute("planLink",planLink);
        return "/planLink/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑生产计划关联数据")
    public RestResponse edit(PlanLink planLink){
        if(null == planLink.getId() || 0 == planLink.getId()){
            return RestResponse.failure("ID不能为空");
        }
        planLinkService.updateById(planLink);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除生产计划关联数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        PlanLink planLink = planLinkService.selectById(id);
        planLink.setDelFlag(true);
        planLinkService.updateById(planLink);
        return RestResponse.success();
    }

}