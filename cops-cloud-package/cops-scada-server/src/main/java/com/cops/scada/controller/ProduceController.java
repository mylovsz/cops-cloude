package com.cops.scada.controller;

import com.cops.scada.entity.Plan;
import com.cops.scada.entity.VO.ProduceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.Produce;
import com.cops.scada.service.ProduceService;
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
 * 生产产品  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-03-19
 */
@Controller
@RequestMapping("/produce")
public class ProduceController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProduceController.class);

    @Autowired
    private ProduceService produceService;

    @PostMapping("getProduceVO")
    @ResponseBody
    public RestResponse getProduceVO(@RequestParam("sn") String sn){
        if(StringUtils.isBlank(sn)){
            return RestResponse.failure("SN不能为空");
        }

        ProduceVO pvo = produceService.getProduceVOBySN(sn);
        if(pvo != null){
            return RestResponse.success().setData(pvo);
        }
        else{
            return RestResponse.failure("不存在该设备");
        }
    }

    @GetMapping("list")
    @SysLog("跳转生产产品列表")
    public String list(Model model){
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        return "/produce/list";
    }

    @RequiresPermissions("scada:produce:list")
    @PostMapping("list")
    @ResponseBody
    @SysLog("请求生产产品列表数据")
    public LayerData<ProduceVO> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<ProduceVO> layerData = new LayerData<>();
        EntityWrapper<ProduceVO> wrapper = new EntityWrapper<>();
        wrapper.eq("produce.del_flag",false);
        wrapper.eq("plan.del_flag",false);
        wrapper.orderBy("produce.sn", true);
        if(!map.isEmpty()){
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

            String customerSn = (String) map.get("customerSn");
            if(StringUtils.isNotBlank(customerSn)) {
                wrapper.like("produce.customer_sn",customerSn.trim());
            }else{
                map.remove("customerSn");
            }

            String state = (String) map.get("state");
            if(StringUtils.isNotBlank(state)) {
                wrapper.eq("produce.state",state);
            }else{
                map.remove("state");
            }

        }
        Page<ProduceVO> pageData = produceService.getProduceVO(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增生产产品页面")
    public String add(){
        return "/produce/add";
    }

    @RequiresPermissions("scada:produce:add")
    @PostMapping("add")
    @SysLog("保存新增生产产品数据")
    @ResponseBody
    public RestResponse add(Produce produce){
        if(StringUtils.isBlank(produce.getSn())){
            return RestResponse.failure("生产编号不能为空");
        }
        produceService.insert(produce);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑生产产品页面")
    public String edit(Long id,Model model){
        Produce produce = produceService.selectById(id);
        model.addAttribute("produce",produce);
        return "/produce/edit";
    }

    @RequiresPermissions("scada:produce:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑生产产品数据")
    public RestResponse edit(Produce produce){
        if(null == produce.getId() || 0 == produce.getId()){
            return RestResponse.failure("ID不能为空");
        }
        if(StringUtils.isBlank(produce.getSn())){
            return RestResponse.failure("生产编号不能为空");
        }
        produceService.updateById(produce);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:produce:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除生产产品数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        Produce produce = produceService.selectById(id);
        produce.setDelFlag(true);
        produceService.updateById(produce);
        return RestResponse.success();
    }

}