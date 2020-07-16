package com.cops.scada.controller;

import com.cops.scada.entity.*;
import com.cops.scada.entity.VO.ExaminesFVO;
import com.cops.scada.entity.VO.RepairVO;
import com.cops.scada.entity.VO.StatisticsRepairVO;
import com.cops.scada.service.FactorySiteService;
import com.cops.scada.service.ProduceService;
import com.cops.scada.service.ProductService;
import com.cops.scada.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.service.RepairService;
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

import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.util.RestResponse;
import com.cops.scada.base.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.util.WebUtils;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
/**
 * <p>
 * 维修管理  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-04-17
 */
@Controller
@RequestMapping("/repair")
public class RepairController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RepairController.class);

    @Autowired
    private RepairService repairService;

    @Autowired
    private FactorySiteService factorySiteService;

    @Autowired
    private ProduceService produceService;

    @Autowired
    private ProductService productService;

    @GetMapping("editTake")
    @SysLog("跳转编辑维修管理页面")
    public String editTake(Long id,Model model){
        Repair repair = repairService.selectById(id);
        model.addAttribute("repair",repair);
        return "/repair/editTake";
    }

    @PostMapping("editTake")
    @ResponseBody
    @SysLog("保存编辑维修管理数据")
    public RestResponse editTake(Repair repair){
        if(null == repair.getId() || 0 == repair.getId()){
            return RestResponse.failure("ID不能为空");
        }
        Repair r = repairService.selectById(repair.getId());
        if(r != null){
            //r.setSn(repair.getSn());
            r.setTakeRemark(repair.getTakeRemark());
            r.setTakeDate(new Date());
            r.setTakeUserId(getCurrentUser().getId());
            r.setState(3); // 取回状态

            repairService.updateById(r);

            return RestResponse.success();
        }

        return RestResponse.failure("不存在该维修工单");
    }

    @GetMapping("listTake")
    @SysLog("跳转维修管理列表")
    public String listTake(){
        return "/repair/listTake";
    }

    @GetMapping("editRepair")
    @SysLog("跳转编辑维修管理页面")
    public String editRepair(Long id,Model model){
        Repair repair = repairService.selectById(id);
        model.addAttribute("repair",repair);
        return "/repair/editRepair";
    }

    @PostMapping("editRepair")
    @ResponseBody
    @SysLog("保存编辑维修管理数据")
    public RestResponse editRepair(Repair repair){
        if(null == repair.getId() || 0 == repair.getId()){
            return RestResponse.failure("ID不能为空");
        }
        Repair r = repairService.selectById(repair.getId());
        if(r != null){
            //r.setSn(repair.getSn());
            r.setFaultCause(repair.getFaultCause());
            r.setResponsibleDepartment(repair.getResponsibleDepartment());
            r.setRepairWay(repair.getRepairWay());
            r.setRepairDate(new Date());
            r.setRepairUserId(getCurrentUser().getId());
            r.setState(repair.getState());

            repairService.updateById(r);

            // 更新产品状态
            Produce p = produceService.getOneByID(r.getProduceId());
            if(p != null){
                switch (r.getState()){
                    case 0:
                        p.setState(5);
                        break;
                    case 1: // 已维修
                        p.setState(6);
                        break;
                    case 2: // 报废
                        p.setState(8);
                        break;
                    default:
                        break;
                }
                produceService.saveProduce(p);
            }
            // 更新产品状态

            return RestResponse.success();
        }

        return RestResponse.failure("不存在该维修工单");
    }

    @GetMapping("listRepair")
    @SysLog("跳转维修管理列表")
    public String listRepair(){
        return "/repair/listRepair";
    }

    @GetMapping("editSend")
    @SysLog("跳转编辑维修管理页面")
    public String editSend(Long id,Model model){
        Repair repair = repairService.selectById(id);
        model.addAttribute("repair",repair);
        return "/repair/editSend";
    }

    @PostMapping("editSend")
    @ResponseBody
    @SysLog("保存编辑维修管理数据")
    public RestResponse editSend(Repair repair){
        if(null == repair.getId() || 0 == repair.getId()){
            return RestResponse.failure("ID不能为空");
        }
        Repair r = repairService.selectById(repair.getId());
        if(r != null){
            //r.setSn(repair.getSn());
            r.setFaultCode(repair.getFaultCode());
            r.setFaultAppearance(repair.getFaultAppearance());
            r.setFaultDate(new Date());
            repairService.updateById(r);
            return RestResponse.success();
        }

        return RestResponse.failure("不存在该维修工单");
    }

    @GetMapping("addSend")
    @SysLog("跳转新增维修管理页面")
    public String addSend(Model model){
        List<FactorySite> pageData = factorySiteService.getListForTree();
        model.addAttribute("factorySite", pageData);
        return "/repair/addSend";
    }

    @PostMapping("addSend")
    @SysLog("保存新增维修管理数据")
    @ResponseBody
    public RestResponse addSend(Repair repair){
        if(StringUtils.isBlank(repair.getSn())){
            return RestResponse.failure("维修单编号不能为空");
        }
        if(repair.getProduceId() == null || repair.getProduceId() == 0){
            return RestResponse.failure("请填写有效产品编号");
        }
        if(repair.getFactorySiteId() == null || repair.getFactorySiteId() == 0){
            return RestResponse.failure("请填写不良站点");
        }

        // 如果已经存在该维修了，则不能再次添加
        Repair r = repairService.getOneByProduceId(repair.getProduceId(), 0);
        if(r != null){
            return RestResponse.failure("该产品已经送修，还未维修结束，只有维修结束后，才可以再次添加。");
        }

        repair.setFaultDate(new Date());
        repair.setFaultUserId(getCurrentUser().getId());
        repair.setState(0); // 未维修
        repairService.insert(repair);

        // 修改产品状态为维修
        Produce p = produceService.getOneByID(repair.getProduceId());
        if(p != null)
        {
            p.setState(5); // 修改为维修
            produceService.saveProduce(p);
        }

        return RestResponse.success();
    }

    @GetMapping("listSend")
    @SysLog("跳转维修管理列表")
    public String listSend(){
        return "/repair/listSend";
    }

    @PostMapping("listSend")
    @ResponseBody
    @SysLog("请求维修管理列表数据")
    public LayerData<Repair> listSend(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                  @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                  ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Repair> layerData = new LayerData<>();
        EntityWrapper<Repair> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String sn = (String) map.get("sn");
            if(StringUtils.isNotBlank(sn)) {
                wrapper.like("sn",sn);
            }else{
                map.remove("sn");
            }

            String faultCode = (String) map.get("faultCode");
            if(StringUtils.isNotBlank(faultCode)) {
                wrapper.eq("fault_code",faultCode);
            }else{
                map.remove("faultCode");
            }

            String beginFaultDate = (String) map.get("beginFaultDate");
            String endFaultDate = (String) map.get("endFaultDate");
            if(StringUtils.isNotBlank(beginFaultDate)) {
                Date begin = DateUtil.parse(beginFaultDate);
                wrapper.ge("fault_date",begin);
            }else{
                map.remove("beginFaultDate");
            }
            if(StringUtils.isNotBlank(endFaultDate)) {
                Date end = DateUtil.parse(endFaultDate);
                wrapper.le("fault_date",end);
            }else{
                map.remove("endFaultDate");
            }

            String beginRepairDate = (String) map.get("beginRepairDate");
            String endRepairDate = (String) map.get("endRepairDate");
            if(StringUtils.isNotBlank(beginRepairDate)) {
                Date begin = DateUtil.parse(beginRepairDate);
                wrapper.ge("repair_date",begin);
            }else{
                map.remove("beginRepairDate");
            }
            if(StringUtils.isNotBlank(endRepairDate)) {
                Date end = DateUtil.parse(endRepairDate);
                wrapper.le("repair_date",end);
            }else{
                map.remove("endRepairDate");
            }

            String beginTakeDate = (String) map.get("beginTakeDate");
            String endTakeDate = (String) map.get("endTakeDate");
            if(StringUtils.isNotBlank(beginTakeDate)) {
                Date begin = DateUtil.parse(beginTakeDate);
                wrapper.ge("take_date",begin);
            }else{
                map.remove("beginTakeDate");
            }
            if(StringUtils.isNotBlank(endTakeDate)) {
                Date end = DateUtil.parse(endTakeDate);
                wrapper.le("take_date",end);
            }else{
                map.remove("endTakeDate");
            }

            String state = (String) map.get("state");
            if(StringUtils.isNotBlank(state)) {
                wrapper.eq("state",state);
            }else{
                map.remove("state");
            }

        }
        Page<Repair> pageData = repairService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("list")
    @SysLog("跳转维修管理列表")
    public String list(){
        return "/repair/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求维修管理列表数据")
    public LayerData<RepairVO> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                    @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                    ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<RepairVO> layerData = new LayerData<>();
        EntityWrapper<RepairVO> wrapper = new EntityWrapper<>();
        wrapper.eq("repair_m.del_flag",false);
        if(!map.isEmpty()){
            String sn = (String) map.get("sn");
            if(StringUtils.isNotBlank(sn)) {
                wrapper.like("repair_m.sn",sn);
            }else{
                map.remove("sn");
            }

            String produceSN = (String) map.get("produce_sn");
            if(StringUtils.isNotBlank(produceSN)) {
                wrapper.like("produce.sn",produceSN);
            }else{
                map.remove("produce_sn");
            }

            String faultCode = (String) map.get("faultCode");
            if(StringUtils.isNotBlank(faultCode)) {
                wrapper.eq("repair_m.fault_code",faultCode);
            }else{
                map.remove("faultCode");
            }

            String beginFaultDate = (String) map.get("beginFaultDate");
            String endFaultDate = (String) map.get("endFaultDate");
            if(StringUtils.isNotBlank(beginFaultDate)) {
                Date begin = DateUtil.parse(beginFaultDate);
                wrapper.ge("repair_m.fault_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginFaultDate");
            }
            if(StringUtils.isNotBlank(endFaultDate)) {
                Date end = DateUtil.parse(endFaultDate);
                wrapper.le("repair_m.fault_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endFaultDate");
            }

            String beginRepairDate = (String) map.get("beginRepairDate");
            String endRepairDate = (String) map.get("endRepairDate");
            if(StringUtils.isNotBlank(beginRepairDate)) {
                Date begin = DateUtil.parse(beginRepairDate);
                wrapper.ge("repair_m.repair_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginRepairDate");
            }
            if(StringUtils.isNotBlank(endRepairDate)) {
                Date end = DateUtil.parse(endRepairDate);
                wrapper.le("repair_m.repair_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endRepairDate");
            }

            String beginTakeDate = (String) map.get("beginTakeDate");
            String endTakeDate = (String) map.get("endTakeDate");
            if(StringUtils.isNotBlank(beginTakeDate)) {
                Date begin = DateUtil.parse(beginTakeDate);
                wrapper.ge("repair_m.take_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginTakeDate");
            }
            if(StringUtils.isNotBlank(endTakeDate)) {
                Date end = DateUtil.parse(endTakeDate);
                wrapper.le("repair_m.take_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endTakeDate");
            }

            String state = (String) map.get("state");
            if(StringUtils.isNotBlank(state)) {
                wrapper.eq("repair_m.state",state);
            }else{
                map.remove("state");
            }

            String responsibleDepartment = (String) map.get("responsibleDepartment");
            if(StringUtils.isNotBlank(responsibleDepartment)) {
                wrapper.eq("repair_m.responsible_department",responsibleDepartment);
            }else{
                map.remove("responsibleDepartment");
            }

            String planId = (String) map.get("planId");
            if(StringUtils.isNotBlank(planId)) {
                wrapper.eq("plan.id",planId);
            }else{
                map.remove("planId");
            }

            String productId = (String) map.get("productId");
            if(StringUtils.isNotBlank(productId)) {
                wrapper.eq("product.id",productId);
            }else{
                map.remove("productId");
            }

            String productType = (String) map.get("productType");
            if(StringUtils.isNotBlank(productType)) {
                wrapper.like("product.type",productType);
            }else{
                map.remove("productType");
            }
        }
        Page<RepairVO> pageData = repairService.getPageRepairVO(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("exportExcel")
    @ResponseBody
    @SysLog("请求维修管理列表数据,导出Excel")
    public void exportExcel(ServletRequest request, HttpServletResponse response){
        System.out.println("vvvvv");
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        EntityWrapper<RepairVO> wrapper = new EntityWrapper<>();
        wrapper.eq("repair_m.del_flag",false);
        if(!map.isEmpty()){
            String sn = (String) map.get("sn");
            if(StringUtils.isNotBlank(sn)) {
                wrapper.like("repair_m.sn",sn);
            }else{
                map.remove("sn");
            }

            String productType = (String) map.get("productType");
            if(StringUtils.isNotBlank(productType)) {
                wrapper.like("product.type",productType);
            }else{
                map.remove("productType");
            }

            String produceSN = (String) map.get("produce_sn");
            if(StringUtils.isNotBlank(produceSN)) {
                wrapper.like("produce.sn",produceSN);
            }else{
                map.remove("produce_sn");
            }

            String faultCode = (String) map.get("faultCode");
            if(StringUtils.isNotBlank(faultCode)) {
                wrapper.eq("repair_m.fault_code",faultCode);
            }else{
                map.remove("faultCode");
            }

            String beginFaultDate = (String) map.get("beginFaultDate");
            String endFaultDate = (String) map.get("endFaultDate");
            if(StringUtils.isNotBlank(beginFaultDate)) {
                Date begin = DateUtil.parse(beginFaultDate);
                wrapper.ge("repair_m.fault_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginFaultDate");
            }
            if(StringUtils.isNotBlank(endFaultDate)) {
                Date end = DateUtil.parse(endFaultDate);
                wrapper.le("repair_m.fault_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endFaultDate");
            }

            String beginRepairDate = (String) map.get("beginRepairDate");
            String endRepairDate = (String) map.get("endRepairDate");
            if(StringUtils.isNotBlank(beginRepairDate)) {
                Date begin = DateUtil.parse(beginRepairDate);
                wrapper.ge("repair_m.repair_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginRepairDate");
            }
            if(StringUtils.isNotBlank(endRepairDate)) {
                Date end = DateUtil.parse(endRepairDate);
                wrapper.le("repair_m.repair_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endRepairDate");
            }

            String beginTakeDate = (String) map.get("beginTakeDate");
            String endTakeDate = (String) map.get("endTakeDate");
            if(StringUtils.isNotBlank(beginTakeDate)) {
                Date begin = DateUtil.parse(beginTakeDate);
                wrapper.ge("repair_m.take_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginTakeDate");
            }
            if(StringUtils.isNotBlank(endTakeDate)) {
                Date end = DateUtil.parse(endTakeDate);
                wrapper.le("repair_m.take_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endTakeDate");
            }

            String state = (String) map.get("state");
            if(StringUtils.isNotBlank(state)) {
                wrapper.eq("repair_m.state",state);
            }else{
                map.remove("state");
            }

            String responsibleDepartment = (String) map.get("responsibleDepartment");
            if(StringUtils.isNotBlank(responsibleDepartment)) {
                wrapper.eq("repair_m.responsible_department",responsibleDepartment);
            }else{
                map.remove("responsibleDepartment");
            }
        }
        List<RepairVO> pageData = repairService.getPageRepairVO(wrapper);
        System.out.println(pageData.size());
        if(pageData != null && pageData.size() > 0){
            //HttpServletResponse response =  getHttpResponse();

            System.out.println("开始");
            long t1 = System.currentTimeMillis();
            ExcelUtils.writeExcel(response, pageData, RepairVO.class, "维修数据");
            long t2 = System.currentTimeMillis();
            String message = String.format("write over! cost:%sms", (t2 - t1));
            System.out.println(message);
        }
    }

    @GetMapping("add")
    @SysLog("跳转新增维修管理页面")
    public String add(){
        return "/repair/add";
    }

    @PostMapping("add")
    @SysLog("保存新增维修管理数据")
    @ResponseBody
    public RestResponse add(Repair repair){
        if(StringUtils.isBlank(repair.getSn())){
            return RestResponse.failure("维修单编号不能为空");
        }
        if(repair.getState() == null){
            return RestResponse.failure("状态不能为空");
        }
        repairService.insert(repair);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑维修管理页面")
    public String edit(Long id,Model model){
        Repair repair = repairService.selectById(id);
        model.addAttribute("repair",repair);
        return "/repair/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑维修管理数据")
    public RestResponse edit(Repair repair){
        if(null == repair.getId() || 0 == repair.getId()){
            return RestResponse.failure("ID不能为空");
        }
        if(StringUtils.isBlank(repair.getSn())){
            return RestResponse.failure("维修单编号不能为空");
        }
        if(repair.getState() == null){
            return RestResponse.failure("状态不能为空");
        }
        repairService.updateById(repair);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除维修管理数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        Repair repair = repairService.selectById(id);
        repair.setDelFlag(true);
        repairService.updateById(repair);
        return RestResponse.success();
    }


    //维修分析报表
    @GetMapping("report")
    @SysLog("跳转维修分析报表")
    public String listReport(Model model){
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        List<Product> productList = productService.selectAll();
        model.addAttribute("productList", productList);
        return "/repair/listRepairReport";
    }

    @PostMapping("listReport")
    @ResponseBody
    @SysLog("请求维修管理列表数据")
    public RestResponse listReport(ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        EntityWrapper<RepairVO> wrapper = new EntityWrapper<>();
        wrapper.eq("repair_m.del_flag",false);
        if(!map.isEmpty()){
            String sn = (String) map.get("sn");
            if(StringUtils.isNotBlank(sn)) {
                wrapper.like("repair_m.sn",sn);
            }else{
                map.remove("sn");
            }

            String productType = (String) map.get("productType");
            if(StringUtils.isNotBlank(productType)) {
                wrapper.like("product.type",productType);
            }else{
                map.remove("productType");
            }

            String produceSN = (String) map.get("produce_sn");
            if(StringUtils.isNotBlank(produceSN)) {
                wrapper.like("produce.sn",produceSN);
            }else{
                map.remove("produce_sn");
            }

            String faultCode = (String) map.get("faultCode");
            if(StringUtils.isNotBlank(faultCode)) {
                wrapper.eq("repair_m.fault_code",faultCode);
            }else{
                map.remove("faultCode");
            }

            String beginFaultDate = (String) map.get("beginFaultDate");
            String endFaultDate = (String) map.get("endFaultDate");
            if(StringUtils.isNotBlank(beginFaultDate)) {
                Date begin = DateUtil.parse(beginFaultDate);
                wrapper.ge("repair_m.fault_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginFaultDate");
            }
            if(StringUtils.isNotBlank(endFaultDate)) {
                Date end = DateUtil.parse(endFaultDate);
                wrapper.le("repair_m.fault_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endFaultDate");
            }

            String beginRepairDate = (String) map.get("beginRepairDate");
            String endRepairDate = (String) map.get("endRepairDate");
            if(StringUtils.isNotBlank(beginRepairDate)) {
                Date begin = DateUtil.parse(beginRepairDate);
                wrapper.ge("repair_m.repair_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginRepairDate");
            }
            if(StringUtils.isNotBlank(endRepairDate)) {
                Date end = DateUtil.parse(endRepairDate);
                wrapper.le("repair_m.repair_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endRepairDate");
            }

            String beginTakeDate = (String) map.get("beginTakeDate");
            String endTakeDate = (String) map.get("endTakeDate");
            if(StringUtils.isNotBlank(beginTakeDate)) {
                Date begin = DateUtil.parse(beginTakeDate);
                wrapper.ge("repair_m.take_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginTakeDate");
            }
            if(StringUtils.isNotBlank(endTakeDate)) {
                Date end = DateUtil.parse(endTakeDate);
                wrapper.le("repair_m.take_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endTakeDate");
            }

            String state = (String) map.get("state");
            if(StringUtils.isNotBlank(state)) {
                wrapper.eq("repair_m.state",state);
            }else{
                map.remove("state");
            }

            String responsibleDepartment = (String) map.get("responsibleDepartment");
            if(StringUtils.isNotBlank(responsibleDepartment)) {
                wrapper.eq("repair_m.responsible_department",responsibleDepartment);
            }else{
                map.remove("responsibleDepartment");
            }

            String planId = (String) map.get("planId");
            if(StringUtils.isNotBlank(planId)) {
                wrapper.eq("plan.id",planId);
            }else{
                map.remove("planId");
            }

            String productId = (String) map.get("productId");
            if(StringUtils.isNotBlank(productId)) {
                wrapper.eq("product.id",productId);
            }else{
                map.remove("productId");
            }
        }
        RestResponse rr = RestResponse.success();
        List<StatisticsRepairVO> faultCode = repairService.getStatisticsRepairVOForFaultCode(wrapper);
        rr.setAny("faultCode", faultCode);
        List<StatisticsRepairVO> responsible = repairService.getStatisticsRepairVOForResponsible(wrapper);
        rr.setAny("responsible", responsible);
        List<StatisticsRepairVO> state = repairService.getStatisticsRepairVOForState(wrapper);
        rr.setAny("state", state);
        return rr;
    }
}