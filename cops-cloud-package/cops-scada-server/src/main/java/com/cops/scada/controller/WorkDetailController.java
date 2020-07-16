package com.cops.scada.controller;

import com.cops.scada.entity.VO.WorkDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.WorkDetail;
import com.cops.scada.service.WorkDetailService;
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
 * 工时详情  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2020-04-08
 */
@Controller
@RequestMapping("/workDetail")
public class WorkDetailController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkDetailController.class);

    @Autowired
    private WorkDetailService workDetailService;

    // V3 start
    @GetMapping("listV3")
    @SysLog("跳转工时详情列表")
    public String listV3(){
        return "/workDetail/listV3";
    }

    @GetMapping("addV3")
    @SysLog("跳转新增工时详情页面")
    public String addV3(){
        return "/workDetail/addV3";
    }

    @PostMapping("addV3")
    @SysLog("保存新增工时详情数据")
    @ResponseBody
    public RestResponse addV3(WorkDetail workDetail){
        workDetail.setStartDate(new Date());
        workDetail.setStartWorkUserId(this.getCurrentUser().getId());
        workDetail.setState(0);
        workDetailService.insert(workDetail);
        return RestResponse.success();
    }

    @GetMapping("editV3")
    @SysLog("跳转编辑工时详情页面")
    public String editV3(Long id,Model model){
        WorkDetail workDetail = workDetailService.selectById(id);
        model.addAttribute("workDetail",workDetail);
        return "/workDetail/edit";
    }
    @GetMapping("resetDispatch")
    @SysLog("跳转调岗分派页面")
    public String resetDispatch(Long id,Model model){
        WorkDetailVO workDetail = workDetailService.getWorkDetailVO(id);
        workDetail.setWorkProcessTime((System.currentTimeMillis() - workDetail.getStartDate().getTime())/1000/60+1);
        model.addAttribute("workDetail",workDetail);
        return "/workDetail/resetDispatch";
    }
    @GetMapping("stopDispatch")
    @SysLog("跳转离岗分派页面")
    public String stopDispatch(Long id,Model model){
        WorkDetailVO workDetail = workDetailService.getWorkDetailVO(id);
        workDetail.setWorkProcessTime((System.currentTimeMillis() - workDetail.getStartDate().getTime())/1000/60+1);
        model.addAttribute("workDetail",workDetail);
        return "/workDetail/stopDispatch";
    }
    @PostMapping("resetDispatch")
    @ResponseBody
    @SysLog("保存调岗分派页面数据")
    public RestResponse resetDispatch(WorkDetail workDetail){
        if(null == workDetail.getId() || 0 == workDetail.getId()){
            return RestResponse.failure("ID不能为空");
        }
        // 结单
        WorkDetail old = workDetailService.selectById(workDetail.getId());
        if(old.getPlanDayId().equals(workDetail.getPlanDayId())){
            return RestResponse.failure("不能新的工单，不能是当前工单，请用离岗功能！");
        }
        if(old == null){
            return RestResponse.failure("当前派工删除，请重新上岗，勿调岗！");
        }
        old.setWorkProcessTime(workDetail.getWorkProcessTime());
        old.setEndQuantityCount(workDetail.getEndQuantityCount());
        old.setEndDate(new Date());
        old.setState(1);
        if(workDetailService.updateById(old) == false){
            return RestResponse.failure("结单失败，请联系管理员！");
        }
        if(workDetail.getStartWorkUserNum() >= old.getStartWorkUserNum()){
            // 说明所有人员都需要转移，不需要延续之前的工单，啥都不做就对了
        } else {
            // 说明还有剩余的人，需要继续工作，此时需要延续之前的工单
            // 开延续工单
            old.setWorkProcessTime(null);
            old.setEndQuantityCount(null);
            old.setEndDate(null);
            old.setState(0);
            old.setId(null);
            old.setStartDate(new Date());
            old.setStartWorkUserNum(old.getStartWorkUserNum() - workDetail.getStartWorkUserNum());
            if(workDetailService.insert(old) == false){
                return RestResponse.failure("结单成功，延续工单失败，请直接执行上岗动作！");
            }
        }

        // 开新的工单
        WorkDetail newWorkDetail = new WorkDetail();
        newWorkDetail.setState(0);
        newWorkDetail.setPlanDayId(workDetail.getPlanDayId());
        newWorkDetail.setPlanId(workDetail.getPlanId());
        newWorkDetail.setJobId(workDetail.getJobId());
        newWorkDetail.setStartDate(new Date());
        newWorkDetail.setStartWorkUserId(getCurrentUser().getId());
        newWorkDetail.setReimbursedTime(workDetail.getReimbursedTime());
        newWorkDetail.setStartWorkUserNum(workDetail.getStartWorkUserNum());
        newWorkDetail.setStartRemarks(workDetail.getStartRemarks());
        newWorkDetail.setStartFinishNum(workDetail.getStartFinishNum());
        newWorkDetail.setDelFlag(false);
        if(workDetailService.insert(newWorkDetail) == false){
            return RestResponse.failure("结单成功，但上岗失败，请直接执行上岗动作！");
        }

        return RestResponse.success();
    }
    @PostMapping("stopDispatch")
    @ResponseBody
    @SysLog("保存离岗分派页面数据")
    public RestResponse stopDispatch(WorkDetail workDetail){
        if(null == workDetail.getId() || 0 == workDetail.getId()){
            return RestResponse.failure("ID不能为空");
        }
        // 结单
        WorkDetail old = workDetailService.selectById(workDetail.getId());
        if(old == null){
            return RestResponse.failure("当前派工删除，请重新上岗，勿调岗！");
        }
        old.setWorkProcessTime(workDetail.getWorkProcessTime());
        old.setEndQuantityCount(workDetail.getEndQuantityCount());
        old.setEndDate(new Date());
        old.setState(1);
        if(workDetailService.updateById(old) == false){
            return RestResponse.failure("结单失败，请联系管理员！");
        }
        if(workDetail.getStartWorkUserNum() >= old.getStartWorkUserNum()){
            // 说明所有人员都需要转移，不需要延续之前的工单，啥都不做就对了
        } else {
            // 说明还有剩余的人，需要继续工作，此时需要延续之前的工单
            // 开延续工单
            old.setWorkProcessTime(null);
            old.setEndQuantityCount(null);
            old.setEndDate(null);
            old.setState(0);
            old.setId(null);
            old.setStartDate(new Date());
            old.setStartWorkUserNum(old.getStartWorkUserNum() - workDetail.getStartWorkUserNum());
            if(workDetailService.insert(old) == false){
                return RestResponse.failure("结单成功，延续工单失败，请直接执行上岗动作！");
            }
        }

        return RestResponse.success();
    }
    // V3 end

    @GetMapping("list")
    @SysLog("跳转工时详情列表")
    public String list(){
        return "/workDetail/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求工时详情列表数据")
    public LayerData<WorkDetailVO> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                        @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                        ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<WorkDetailVO> layerData = new LayerData<>();
        EntityWrapper<WorkDetailVO> wrapper = new EntityWrapper<>();
        wrapper.eq("work_detail.del_flag",false);
        wrapper.eq("plan.del_flag",false);
        wrapper.orderBy("plan.nc_id")
                .orderBy("job.sn")
                .orderBy("work_user.nick_name")
                .orderBy("work_detail.state")
                .orderBy("work_detail.start_date");
        if(!map.isEmpty()){

            String startDate = (String)map.get("startDate");
            if(StringUtils.isNotBlank(startDate)){
                Date start = DateUtil.parse(startDate);
                wrapper.between("work_detail.start_date",
                        com.cops.scada.util.DateUtil.getStartTime(start),
                        com.cops.scada.util.DateUtil.getEndTime(start));
            }else{
                map.remove("startDate");
            }

            String beginStartDate = (String) map.get("beginStartDate");
            String endStartDate = (String) map.get("endStartDate");
            if(StringUtils.isNotBlank(beginStartDate)) {
                Date begin = DateUtil.parse(beginStartDate);
                wrapper.ge("work_detail.start_date",begin);
            }else{
                map.remove("beginStartDate");
            }
            if(StringUtils.isNotBlank(endStartDate)) {
                Date end = DateUtil.parse(endStartDate);
                wrapper.le("work_detail.start_date",end);
            }else{
                map.remove("endStartDate");
            }

            String beginEndDate = (String) map.get("beginEndDate");
            String endEndDate = (String) map.get("endEndDate");
            if(StringUtils.isNotBlank(beginEndDate)) {
                Date begin = DateUtil.parse(beginEndDate);
                wrapper.ge("work_detail.end_date",begin);
            }else{
                map.remove("beginEndDate");
            }
            if(StringUtils.isNotBlank(endEndDate)) {
                Date end = DateUtil.parse(endEndDate);
                wrapper.le("work_detail.end_date",end);
            }else{
                map.remove("endEndDate");
            }

            String state = (String) map.get("state");
            if(StringUtils.isNotBlank(state)) {
                wrapper.eq("work_detail.state",state);
            }else{
                map.remove("state");
            }

        }
        Page<WorkDetailVO> pageData = workDetailService.getWorkDetailVO(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增工时详情页面")
    public String add(){
        return "/workDetail/add";
    }

    @PostMapping("add")
    @SysLog("保存新增工时详情数据")
    @ResponseBody
    public RestResponse add(WorkDetail workDetail){
        workDetailService.insert(workDetail);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑工时详情页面")
    public String edit(Long id,Model model){
        WorkDetail workDetail = workDetailService.selectById(id);
        model.addAttribute("workDetail",workDetail);
        return "/workDetail/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑工时详情数据")
    public RestResponse edit(WorkDetail workDetail){
        if(null == workDetail.getId() || 0 == workDetail.getId()){
            return RestResponse.failure("ID不能为空");
        }
        workDetailService.updateById(workDetail);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除工时详情数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        WorkDetail workDetail = workDetailService.selectById(id);
        workDetail.setDelFlag(true);
        workDetailService.updateById(workDetail);
        return RestResponse.success();
    }

}