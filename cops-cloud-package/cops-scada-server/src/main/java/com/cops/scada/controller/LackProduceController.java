package com.cops.scada.controller;

import com.cops.scada.entity.Plan;
import com.cops.scada.entity.VO.LackProduceVO;
import com.google.common.primitives.Longs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.LackProduce;
import com.cops.scada.service.LackProduceService;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
 * 跳流程产品  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@Controller
@RequestMapping("/lackProduce")
public class LackProduceController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LackProduceController.class);

    @Autowired
    private LackProduceService lackProduceService;

    @GetMapping("list")
    @SysLog("跳转跳流程产品列表")
    public String list(Model model){
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        return "/lackProduce/list";
    }

    @PostMapping("list")
    @ResponseBody
    public LayerData<LackProduceVO> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                         @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                         ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<LackProduceVO> layerData = new LayerData<>();
        EntityWrapper<LackProduceVO> wrapper = new EntityWrapper<>();
        wrapper.eq("lack_produce.del_flag",false);
        wrapper.orderBy("plan.sn", false);
        Boolean isUnion = false;
        if(!map.isEmpty()){
            String type = (String) map.get("type");
            if(StringUtils.isNotBlank(type)) {
                wrapper.eq("lack_produce.type",type);
            }else{
                map.remove("type");
            }

            String current_site = (String) map.get("current_site");
            if(StringUtils.isNotBlank(current_site)) {
                wrapper.eq("lack_produce.current_site",current_site);
            }else{
                map.remove("current_site");
            }

            String planSn = (String) map.get("planId");
            if(StringUtils.isNotBlank(planSn)) {
                wrapper.eq("plan.id",planSn);
            }else{
                map.remove("planId");
            }

            String productType = (String) map.get("productType");
            if(StringUtils.isNotBlank(productType)) {
                wrapper.like("product.type",productType);
            }else{
                map.remove("productType");
            }

            String sn = (String) map.get("sn");
            if(StringUtils.isNotBlank(sn)) {
                wrapper.like("produce.sn",sn.trim());
            }else{
                map.remove("sn");
            }

            String state = (String) map.get("state");
            if(StringUtils.isNotBlank(state)) {
                wrapper.eq("lack_produce.state",state);
            }else{
                map.remove("state");
            }

            String union = (String) map.get("union");
            if(StringUtils.isNotBlank(union)) {
                isUnion = true;
            }else{
                map.remove("union");
            }

            String timeout = (String) map.get("timeout");
            if(StringUtils.isNotBlank(timeout)) {
                //int sec= Integer.parseInt(timeout);
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar nowTime = Calendar.getInstance();
                //System.out.println(sdf.format(nowTime.getTime()));
                //nowTime.add(Calendar.SECOND, sec);//增加指定秒数
                //System.out.println(sdf.format(nowTime.getTime()));
                //wrapper.ge("lack_produce.create_date", nowTime.getTime());
                wrapper.le("lack_produce.create_date", nowTime.getTime());
            }else{
                map.remove("create_date");
            }


            String beginCollectDate = (String) map.get("beginCollectDate");
            String endCollectDate = (String) map.get("endCollectDate");
            if(StringUtils.isNotBlank(beginCollectDate)) {
                Date begin = DateUtil.parse(beginCollectDate);
                wrapper.ge("lack_produce.collect_date", com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginCollectDate");
            }
            if(StringUtils.isNotBlank(endCollectDate)) {
                Date end = DateUtil.parse(endCollectDate);
                wrapper.le("lack_produce.collect_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endCollectDate");
            }

        }
        Page<LackProduceVO> pageData;
        if(isUnion)
            pageData = lackProduceService.getPageLackProduceVOUnion(new Page<>(page,limit),wrapper);
        else
            pageData = lackProduceService.getPageLackProduceVO(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }


    @PostMapping("getLackProduceByProduceSn")
    @ResponseBody
    @SysLog("请求跳流程产品列表数据")
    public LayerData<LackProduceVO> getLackProduceByProduceSn(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                         @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                         ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<LackProduceVO> layerData = new LayerData<>();
        EntityWrapper<LackProduceVO> wrapper = new EntityWrapper<>();
        wrapper.eq("scada_lack_produce.del_flag",false);
        wrapper.eq("scada_produce.sn",map.get("sn"));

        Page<LackProduceVO> pageData = lackProduceService.getLackProduceByProduceSn(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增跳流程产品页面")
    public String add(){
        return "/lackProduce/add";
    }

    @PostMapping("add")
    @SysLog("保存新增跳流程产品数据")
    @ResponseBody
    public RestResponse add(LackProduce lackProduce){
        lackProduceService.insert(lackProduce);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑跳流程产品页面")
    public String edit(Long id,Model model){
        LackProduce lackProduce = lackProduceService.selectById(id);
        model.addAttribute("lackProduce",lackProduce);
        return "/lackProduce/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑跳流程产品数据")
    public RestResponse edit(LackProduce lackProduce){
        if(null == lackProduce.getId() || 0 == lackProduce.getId()){
            return RestResponse.failure("ID不能为空");
        }
        lackProduceService.updateById(lackProduce);
        return RestResponse.success();
    }
    @PostMapping("chuli")
    @ResponseBody
    @SysLog("处理跳流程产品数据")
    public RestResponse chuli(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        LackProduce lackProduce = lackProduceService.selectById(id);
        lackProduce.setState(1);
        lackProduceService.updateById(lackProduce);
        return RestResponse.success();
    }
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除跳流程产品数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        LackProduce lackProduce = lackProduceService.selectById(id);
        lackProduce.setDelFlag(true);
        lackProduceService.updateById(lackProduce);
        return RestResponse.success();
    }

}