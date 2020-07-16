package com.cops.scada.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.entity.nc65.BatchMaterial;
import com.cops.entity.nc65.DTO.WrapperDTO;
import com.cops.entity.nc65.ProductionOrder;
import com.cops.library.until.DataConvert;
import com.cops.library.until.NC65DataConverter;
import com.cops.library.until.NC65RestResponse;
import com.cops.scada.annotation.SysLog;
import com.cops.scada.base.BaseController;
import com.cops.scada.entity.DTO.SlavePlanDTO;
import com.cops.scada.entity.Plan;
import com.cops.scada.entity.PlanLink;
import com.cops.scada.entity.Produce;
import com.cops.scada.entity.Product;
import com.cops.scada.entity.VO.PlanLinkSlaveVO;
import com.cops.scada.entity.VO.PlanProductVO;
import com.cops.scada.service.*;
import com.cops.scada.util.LayerData;
import com.cops.scada.util.RestResponse;
import com.xiaoleilu.hutool.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 生产计划  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-03-14
 */
@Controller
@RequestMapping("/plan")
public class PlanController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlanController.class);

    @Autowired
    private SyncNCAndScadaService syncNCAndScadaService;

    @Autowired
    private NC65ProductionOrderService nc65ProductionOrderService;

    @Autowired
    private NC65BatchMaterialService nc65BatchMaterialService;

    @Autowired
    private PlanService planService;

    @Autowired
    private PlanLinkService planLinkService;

    @Autowired
    private ProduceService produceService;

    // v3版本的内容 start
    @GetMapping("listV3")
    @SysLog("列表")
    public String listV3(Model model){
        List<Product> productList = productService.selectAll();
        model.addAttribute("productSNList", productList);
        return "/plan/listV3";
    }
    @GetMapping("addV3")
    @SysLog("跳转新增生产计划页面")
    public String addV3(Model model) {
        List<Product> products = productService.selectAll();
        model.addAttribute("productList", products);
        return "/plan/addV3";
    }

    @RequiresPermissions("scada:plan:add")
    @PostMapping("addV3")
    @SysLog("保存新增生产计划数据")
    @ResponseBody
    public RestResponse addV3(Plan plan) {
        if (StringUtils.isBlank(plan.getSn())) {
            return RestResponse.failure("生产工单号不能为空");
        }
        if(planService.existsBySN(plan.getSn())){
            return RestResponse.failure("该生产工单号已经存在");
        }
        if (plan.getState() == null) {
            return RestResponse.failure("状态不能为空");
        }
        //planService.insert(plan);
        planService.addPlan(plan);
        return RestResponse.success();
    }

    @GetMapping("addPlanLink")
    @SysLog("跳转新增子生产计划页面")
    public String addPlanLink(Model model) {
        List<Product> products = productService.selectAll();
        model.addAttribute("productList", products);
        return "/plan/addPlanLink";
    }

    @RequiresPermissions("scada:plan:add")
    @PostMapping("addPlanLink")
    @SysLog("保存新增子生产计划数据")
    @ResponseBody
    public RestResponse addPlanLink(@RequestParam("masterPlanId") String masterPlanId, Plan slavePlan) {
        if(StringUtils.isBlank(masterPlanId)){
            return RestResponse.failure("主计划不存在，请核实系统数据的一致性！");
        }
        // 判断子计划数据
        if (StringUtils.isBlank(slavePlan.getSn())) {
            return RestResponse.failure("生产工单号不能为空");
        }
        if (slavePlan.getState() == null) {
            return RestResponse.failure("状态不能为空");
        }
        // 检索数据订单是否存在
        Plan planResult = planService.getPlanBySN(slavePlan.getSn());
        if(planResult == null){
            // 插入该订单
            slavePlan.setType(1); // 半成品计划
            planService.addPlan(slavePlan);
            planResult = slavePlan;
        }
        // 检索是否存在关联
        PlanLinkSlaveVO planLinkSlaveVO = planLinkService.getPlanLinkSlaveVO(Long.parseLong(masterPlanId), planResult.getId());
        if(planLinkSlaveVO != null) {
            // 说明已经存在了，不能重复添加
            return RestResponse.failure("子计划已经存在，请勿重复添加！");
        }
        // 加入关联
        PlanLink planLink = new PlanLink();
        planLink.setMasterPlanId(Long.parseLong(masterPlanId));
        planLink.setSlavePlanId(planResult.getId());
        if(planLinkService.insert(planLink)){
            return RestResponse.success();
        } else {
            return RestResponse.failure("插入子计划失败，请联系系统管理员，进行排查！");
        }
    }

    // v3版本的内容 end

    @RequiresPermissions("scada:plan:going")
    @PostMapping("going")
    @SysLog("生产计划-投产")
    @ResponseBody
    public RestResponse going(@RequestParam(value = "id", required = false) Long id) {
        if (null == id || 0 == id) {
            return RestResponse.failure("ID不能为空");
        }
        String error = "发送错误";
        try {

            Plan plan = planService.selectById(id);
            Integer successCount = planService.going(plan);
            // 修改plan为投产中
            plan.setState(1); // 1-投产
            planService.updateById(plan);
            return RestResponse.success().setMessage(successCount.toString());
        }catch (NumberFormatException ex){
            error = "标签格式不正确，请保证后5位为数字";
        }catch (Exception ex){
            error = ex.getMessage();
        }

        return RestResponse.failure(error);
    }

//    @GetMapping("list")
//    @SysLog("跳转生产计划列表")
//    public String list() {
//        return "/plan/list";
//    }

    @GetMapping("list")
    @SysLog("列表")
    public String list(Model model){
        List<Product> productList = productService.selectAll();
        model.addAttribute("productSNList", productList);
        return "/plan/list";
    }

    @RequiresPermissions("scada:plan:list")
    @PostMapping("list")
    @ResponseBody
    @SysLog("请求生产计划列表数据")
    public LayerData<PlanProductVO> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                         ServletRequest request) {
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<PlanProductVO> layerData = new LayerData<>();
        EntityWrapper<PlanProductVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan.del_flag", false);
        wrapper.orderBy("plan.create_date", false);
        if (!map.isEmpty()) {
            String sn = (String) map.get("sn");
            if (StringUtils.isNotBlank(sn)) {
                wrapper.like("plan.sn", sn.trim());
            } else {
                map.remove("sn");
            }

            String type = (String) map.get("type");
            if(StringUtils.isNotBlank(type)) {
                wrapper.like("product.type",type);
            }else{
                map.remove("type");
            }

            String productSN = (String) map.get("productSN");
            if(StringUtils.isNotBlank(productSN)) {
                wrapper.like("product.sn",productSN.trim());
            }else{
                map.remove("productSN");
            }

            String quantity = (String) map.get("quantity");
            if (StringUtils.isNotBlank(quantity)) {
                wrapper.like("plan.quantity", quantity);
            } else {
                map.remove("quantity");
            }

            String lableRange = (String) map.get("lableRange");
            if (StringUtils.isNotBlank(lableRange)) {
                wrapper.like("plan.lable_range", lableRange);
            } else {
                map.remove("lableRange");
            }

            String beginStartDate = (String) map.get("beginStartDate");
            String endStartDate = (String) map.get("endStartDate");
            if (StringUtils.isNotBlank(beginStartDate)) {
                Date begin = DateUtil.parse(beginStartDate);
                wrapper.ge("plan.start_date", begin);
            } else {
                map.remove("beginStartDate");
            }
            if (StringUtils.isNotBlank(endStartDate)) {
                Date end = DateUtil.parse(endStartDate);
                wrapper.le("plan.start_date", end);
            } else {
                map.remove("endStartDate");
            }

            String beginEndDate = (String) map.get("beginEndDate");
            String endEndDate = (String) map.get("endEndDate");
            if (StringUtils.isNotBlank(beginEndDate)) {
                Date begin = DateUtil.parse(beginEndDate);
                wrapper.ge("plan.end_date", begin);
            } else {
                map.remove("beginEndDate");
            }
            if (StringUtils.isNotBlank(endEndDate)) {
                Date end = DateUtil.parse(endEndDate);
                wrapper.le("plan.end_date", end);
            } else {
                map.remove("endEndDate");
            }

            String beginTagStartDate = (String) map.get("beginTagStartDate");
            String endTagStartDate = (String) map.get("endTagStartDate");
            if (StringUtils.isNotBlank(beginTagStartDate)) {
                Date begin = DateUtil.parse(beginTagStartDate);
                wrapper.ge("plan.tag_start_date", begin);
            } else {
                map.remove("beginTagStartDate");
            }
            if (StringUtils.isNotBlank(endTagStartDate)) {
                Date end = DateUtil.parse(endTagStartDate);
                wrapper.le("plan.tag_start_date", end);
            } else {
                map.remove("endTagStartDate");
            }

            String beginTagEndDate = (String) map.get("beginTagEndDate");
            String endTagEndDate = (String) map.get("endTagEndDate");
            if (StringUtils.isNotBlank(beginTagEndDate)) {
                Date begin = DateUtil.parse(beginTagEndDate);
                wrapper.ge("plan.tag_end_date", begin);
            } else {
                map.remove("beginTagEndDate");
            }
            if (StringUtils.isNotBlank(endTagEndDate)) {
                Date end = DateUtil.parse(endTagEndDate);
                wrapper.le("plan.tag_end_date", end);
            } else {
                map.remove("endTagEndDate");
            }

            String state = (String) map.get("state");
            if (StringUtils.isNotBlank(state)) {
                wrapper.eq("plan.state", state);
            } else {
                map.remove("state");
            }

            // v3 相关的参数
            String planType = (String) map.get("planType");
            if(StringUtils.isNotBlank(planType)) {
                wrapper.eq("plan.type",planType);
            }else{
                map.remove("planType");
            }

            String planNcId = (String) map.get("ncId");
            if(StringUtils.isNotBlank(planNcId)) {
                wrapper.eq("plan.nc_id",planNcId);
            }else{
                map.remove("ncId");
            }

            String planRule = (String) map.get("planRule");
            if(StringUtils.isNotBlank(planRule)) {
                wrapper.eq("plan.rule",planRule);
            }else{
                map.remove("planRule");
            }
        }
        //Page<Plan> pageData = planService.selectPage(new Page<>(page,limit),wrapper);
        Page<PlanProductVO> pageData = planService.getPlanProduct(new Page<>(page, limit), wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增生产计划页面")
    public String add(Model model) {
        List<Product> products = productService.selectAll();
        model.addAttribute("productList", products);
        return "/plan/add";
    }

    @RequiresPermissions("scada:plan:add")
    @PostMapping("add")
    @SysLog("保存新增生产计划数据")
    @ResponseBody
    public RestResponse add(Plan plan) {
        if (StringUtils.isBlank(plan.getSn())) {
            return RestResponse.failure("生产工单号不能为空");
        }
        if(planService.existsBySN(plan.getSn())){
            return RestResponse.failure("该生产工单号已经存在");
        }
        if (plan.getState() == null) {
            return RestResponse.failure("状态不能为空");
        }
        //planService.insert(plan);
        planService.addPlan(plan);
        return RestResponse.success().setMessage(plan.getId()+"");
    }

    @PostMapping("nc65BatchMaterialList")
    @ResponseBody
    @SysLog("跳转NC系统信息")
    public LayerData<BatchMaterial> nc65BatchMaterialList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                      @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<BatchMaterial> layerData = new LayerData<>();

        if (!map.isEmpty()) {
            String planId = (String) map.get("planId");

            if (StringUtils.isNotBlank(planId)) {
                Plan plan = planService.selectById(planId);

                String pmoBillCode = null;

                if(StringUtils.isNotBlank(plan.getNcId())){
                    pmoBillCode = plan.getNcId();
                }
                else {
                    pmoBillCode = NC65DataConverter.productionOrderSnToId(plan.getSn());
                }

                if(StringUtils.isNotBlank(pmoBillCode)) {
                    WrapperDTO wrapperDTO = new WrapperDTO();
                    wrapperDTO.setPage(page);
                    wrapperDTO.setLimit(limit);
                    BatchMaterial batchMaterial = new BatchMaterial();
                    batchMaterial.setPmoBillCode(pmoBillCode);
                    batchMaterial.setOrgsCode("LLSC");
                    wrapperDTO.setWrapper(batchMaterial);
                    NC65RestResponse nc65RestResponse = nc65BatchMaterialService.getBatchMaterial(wrapperDTO);
                    if(nc65RestResponse != null && nc65RestResponse.getData() != null){
                        layerData = (LayerData<BatchMaterial>) DataConvert.mapToObject((Map<String, Object>) nc65RestResponse.getData(), LayerData.class);
                    }
                }
            }
        }

        return layerData;
    }

    @GetMapping("nc65BatchMaterial")
    @SysLog("跳转NC系统信息")
    public String nc65BatchMaterial(String id, Model model){
        Plan plan = planService.selectById(id);
        model.addAttribute("planId", plan.getId());

        return "/plan/nc65BatchMaterialInfo";
    }

    @GetMapping("nc65ProductionOrder")
    @SysLog("跳转NC系统信息")
    public String nc65ProductionOrder(String id, Model model){
        Plan plan = planService.selectById(id);
        String planSn = plan.getSn();
        model.addAttribute("planSn", planSn);

        List<ProductionOrder> productionOrders = syncNCAndScadaService.getNCProductionOrderByPlan(plan);
        if(productionOrders!= null && productionOrders.size()>0){
            model.addAttribute("productionOrder", productionOrders.get(0));
        }

        return "/plan/nc65ProductionOrderInfo";
    }

    @GetMapping("nc65BatchMaterialBySn")
    @SysLog("跳转NC系统信息")
    public String nc65BatchMaterialBySn(String sn, Model model){
        Plan plan = planService.getPlanBySN(sn);
        model.addAttribute("planId", plan.getId());

        return "/plan/nc65BatchMaterialInfo";
    }

    @GetMapping("nc65ProductionOrderBySn")
    @SysLog("跳转NC系统信息")
    public String nc65ProductionOrderBySn(String sn, Model model){
        Plan plan = planService.getPlanBySN(sn);
        String planSn = plan.getSn();
        model.addAttribute("planSn", planSn);

        List<ProductionOrder> productionOrders = syncNCAndScadaService.getNCProductionOrderByPlan(plan);
        if(productionOrders!= null && productionOrders.size()>0){
            model.addAttribute("productionOrder", productionOrders.get(0));
        }

        return "/plan/nc65ProductionOrderInfo";
    }

    @GetMapping("edit")
    @SysLog("跳转编辑生产计划页面")
    public String edit(Long id, Model model) {
        Plan plan = planService.selectById(id);
        model.addAttribute("plan", plan);
        List<Product> products = productService.selectAll();
        model.addAttribute("productList", products);
        return "/plan/edit";
    }

    @RequiresPermissions("scada:plan:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑生产计划数据")
    public RestResponse edit(Plan plan) {
        if (null == plan.getId() || 0 == plan.getId()) {
            return RestResponse.failure("ID不能为空");
        }
        if (StringUtils.isBlank(plan.getSn())) {
            return RestResponse.failure("生产工单号不能为空");
        }
        if(planService.existsBySN(plan.getSn(), plan.getId())){
            return RestResponse.failure("该生产工单号已经存在");
        }
        if (plan.getState() == null) {
            return RestResponse.failure("状态不能为空");
        }
        planService.updateById(plan);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:plan:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除生产计划数据")
    public RestResponse delete(@RequestParam(value = "id", required = false) Long id) {
        if (null == id || 0 == id) {
            return RestResponse.failure("ID不能为空");
        }
        Plan plan = planService.selectById(id);
        plan.setDelFlag(true);
        planService.updateById(plan);
        return RestResponse.success();
    }

}