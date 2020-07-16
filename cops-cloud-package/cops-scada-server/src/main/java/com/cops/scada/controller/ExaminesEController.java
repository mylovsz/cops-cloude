package com.cops.scada.controller;

import com.cops.library.until.DateConvert;
import com.cops.scada.entity.Plan;
import com.cops.scada.entity.Product;
import com.cops.scada.entity.VO.ExaminesEVO;
import com.cops.scada.entity.VO.QualityExaminesVO;
import com.cops.scada.entity.VO.ResponseCPK;
import com.cops.scada.service.PlanParameterService;
import com.cops.scada.util.CPKUtil;
import com.cops.scada.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.ExaminesE;
import com.cops.scada.service.ExaminesEService;
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

import java.util.*;

import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.util.RestResponse;
import com.cops.scada.base.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.web.util.WebUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
/**
 * <p>
 * 点火数据  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-03-20
 */
@Controller
@RequestMapping("/examinesE")
public class ExaminesEController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExaminesEController.class);

    @Autowired
    private ExaminesEService examinesEService;

    @Autowired
    private PlanParameterService planParameterService;

    @RequiresPermissions("scada:examinesE:cpk")
    @PostMapping("cpk")
    @ResponseBody
    @SysLog("计算cpk")
    public RestResponse cpk(String planId){

        // 计算cpk的参数
        Long planid;
        Double vU;
        Double vL;
        Double tU;
        Double tL;
        try {
            planid = Long.parseLong(planId);
            vU = planParameterService.getHotVU(planid);
            vL = planParameterService.getHotVL(planid);
            tU = planParameterService.getHotTU(planid);
            tL = planParameterService.getHotTL(planid);
        }
        catch (Exception ex)
        {
            return RestResponse.failure("参数错误，检查是否配置工单的统计参数");
        }

        try {
            List<ExaminesEVO> list = examinesEService.getAllExaminesEVO(planId);
            ArrayList<Double> v = new ArrayList<>();
            ArrayList<Double> l = new ArrayList<>();
            Double d;
            String temp;
            for (ExaminesEVO e :
                    list) {
                temp = e.getMeterB().trim();
                if (temp != null && temp.length() > 0) {
                    d = Double.parseDouble(temp);
                    if (d > 0) v.add(d);
                }

                temp = e.getMeterE().trim();
                if (temp != null && temp.length() > 0) {
                    d = Double.parseDouble(temp);
                    if (d > 0) l.add(d);
                }
            }
            Double[] cpk = new Double[v.size()];
            v.toArray(cpk);
            ResponseCPK vCPK = CPKUtil.getCPK(cpk, vU, vL);
            cpk = new Double[l.size()];
            l.toArray(cpk);
            ResponseCPK lCPK = CPKUtil.getCPK(cpk, tU, tL);
            RestResponse rp = RestResponse.success();
            rp.setAny("vCPK", vCPK);
            rp.setAny("lCPK", lCPK);
            return rp;
        }
        catch (Exception ex){
            return RestResponse.failure(ex.getMessage());
        }
    }

    @PostMapping("qualityDay")
    @ResponseBody
    @SysLog("请求日质量数据")
    public LayerData<QualityExaminesVO> qualityDay(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                                   @RequestParam(value = "collectDate")Date collectDate){
        LayerData<QualityExaminesVO> layerData = new LayerData<>();

        Date start = DateConvert.getStartTime(collectDate);
        Date end = DateConvert.getEndTime(collectDate);

        Page<QualityExaminesVO> qe = examinesEService.getQulityDay1(new Page<QualityExaminesVO>(page, limit) , start,end);
        layerData.setData(qe.getRecords());
        layerData.setCount(qe.getTotal());
        return layerData;
    }

    @PostMapping("qualityDayKanban")
    @ResponseBody
    public LayerData<QualityExaminesVO> qualityDayKanban(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                         @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                                         @RequestParam(value = "collectDate") Date collectDate
    ){
        LayerData<QualityExaminesVO> layerData = new LayerData<>();

        Date start = DateConvert.getStartTime(collectDate);
        Date end = DateConvert.getEndTime(collectDate);

        List<QualityExaminesVO> qe = examinesEService.getQulityDayKanban(start, end);
        if(qe != null) {
            Collections.sort(qe);
            layerData.setData(qe);
            layerData.setCount(qe.size());
        } else {
            layerData.setData(null);
            layerData.setCount(0);
        }
        return layerData;
    }

    @PostMapping("qualityDay1")
    @ResponseBody
    @SysLog("请求日质量数据")
    public LayerData<QualityExaminesVO> qualityDay1(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        LayerData<QualityExaminesVO> layerData = new LayerData<>();

        Date start = DateConvert.getStartTime(new Date());
        Date end = DateConvert.getEndTime(new Date());

        Page<QualityExaminesVO> qe = examinesEService.getQulityDay1(new Page<QualityExaminesVO>(page, limit) , start,end);
        layerData.setData(qe.getRecords());
        layerData.setCount(qe.getTotal());
        return layerData;
    }

    @PostMapping("qualityDay3")
    @ResponseBody
    @SysLog("请求当日质量数据")
    public LayerData<QualityExaminesVO> qualityDay(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                                   ServletRequest request ){

        Map map = WebUtils.getParametersStartingWith(request, "s_");
        EntityWrapper<QualityExaminesVO> wrapper = new EntityWrapper<>();

        Date start = DateConvert.getStartTime(new Date());
        Date end = DateConvert.getEndTime(new Date());
        if(!map.isEmpty()) {

            String beginManufactureDate = (String) map.get("beginManufactureDate");
            String endManufactureDate = (String) map.get("endManufactureDate");

            if(StringUtils.isNotBlank(beginManufactureDate)) {
                Date begin = DateUtil.parse(beginManufactureDate);
                wrapper.ge("qualityExaminesVO.collect_date",com.cops.scada.util.DateUtil.getStartTime(begin));
                start=DateConvert.getStartTime(DateUtil.parse(beginManufactureDate));
                end = DateConvert.getEndTime(DateUtil.parse(beginManufactureDate));
            }else{
                map.remove("qualityExaminesVO.collect_date");
            }
            if(StringUtils.isNotBlank(endManufactureDate)) {
                Date endt = DateUtil.parse(endManufactureDate);
                wrapper.le("qualityExaminesVO.collect_date",com.cops.scada.util.DateUtil.getEndTime(endt));
                end = DateConvert.getEndTime(DateUtil.parse(endManufactureDate));
            }else{
                map.remove("qualityExaminesVO.collect_date");
            }

            String productSN = (String) map.get("productSn");
            if(StringUtils.isNotBlank(productSN)) {
                wrapper.like("qualityExaminesVO.product_sn",productSN.trim());
            }else{
                map.remove("qualityExaminesVO.product_sn");
            }

            String planId = (String) map.get("planSn");
            if(StringUtils.isNotBlank(planId)) {
                wrapper.eq("qualityExaminesVO.plan_sn",planId);
            }else{
                map.remove("qualityExaminesVO.plan_sn");
            }
        }
        LayerData<QualityExaminesVO> layerData = new LayerData<>();

        Page<QualityExaminesVO> qe = examinesEService.getQulityDay3(new Page<QualityExaminesVO>(page, limit),start,end,wrapper );
        layerData.setData(qe.getRecords());
        layerData.setCount(qe.getTotal());
        return layerData;
    }

    @RequiresPermissions("scada:examinesE:removeDup")
    @PostMapping("removeDup")
    @ResponseBody
    @SysLog("去除重复数据")
    public RestResponse removeDup(String productSN){
        Long count = examinesEService.deleteDup();
        return RestResponse.success().setMessage("删除重复数据："+count);
    }

    @GetMapping("list")
    @SysLog("跳转点火数据列表")
    public String list(Model model){
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        List<Product> productList = productService.selectAll();
        model.addAttribute("productSNList", productList);
        return "/examinesE/list";
    }

    @RequiresPermissions("scada:examinesE:list")
    @PostMapping("list")
    @ResponseBody
    @SysLog("请求点火数据列表数据")
    public LayerData<ExaminesEVO> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                       @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                       ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<ExaminesEVO> layerData = new LayerData<>();
        EntityWrapper<ExaminesEVO> wrapper = new EntityWrapper<>();
        wrapper.eq("examines_e.del_flag",false);
        wrapper.eq("plan.del_flag",false);
        wrapper.orderBy("produce.sn", true);
        if(!map.isEmpty()){
            String planId = (String) map.get("planId");
            if(StringUtils.isNotBlank(planId)) {
                wrapper.eq("plan.id",planId);
            }else{
                map.remove("planId");
            }

            String productType = (String) map.get("productType");
            if(StringUtils.isNotBlank(productType)) {
                wrapper.like("product.type",productType);
            }else{
                map.remove("productType");
            }

            String productSN = (String) map.get("productSN");
            if(StringUtils.isNotBlank(productSN)) {
                wrapper.like("product.sn",productSN.trim());
            }else{
                map.remove("productSN");
            }


            String produceSn = (String) map.get("produceSn");
            if(StringUtils.isNotBlank(produceSn)) {
                wrapper.like("produce.sn",produceSn.trim());
            }else{
                map.remove("produceSn");
            }

            String beginCollectDate = (String) map.get("beginCollectDate");
            String endCollectDate = (String) map.get("endCollectDate");
            if(StringUtils.isNotBlank(beginCollectDate)) {
                Date begin = DateUtil.parse(beginCollectDate);
                wrapper.ge("examines_e.collect_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginCollectDate");
            }
            if(StringUtils.isNotBlank(endCollectDate)) {
                Date end = DateUtil.parse(endCollectDate);
                wrapper.le("examines_e.collect_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endCollectDate");
            }

            String result = (String) map.get("result");
            if(StringUtils.isNotBlank(result)) {
                wrapper.like("examines_e.result",result);
            }else{
                map.remove("result");
            }
            String error = (String) map.get("error");
            if(StringUtils.isNotBlank(error)) {
                wrapper.andNew().ne("examines_e.result","PASS");
                wrapper.or().isNull("examines_e.result");
            }else{
                map.remove("error");
            }
        }
        Page<ExaminesEVO> pageData = examinesEService.getExaminesEVO(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增点火数据页面")
    public String add(){
        return "/examinesE/add";
    }

    @RequiresPermissions("scada:examinesE:add")
    @PostMapping("add")
    @SysLog("保存新增点火数据数据")
    @ResponseBody
    public RestResponse add(ExaminesE examinesE){
        if(examinesE.getProduceId() == null){
            return RestResponse.failure("生产产品不能为空");
        }
        examinesEService.insert(examinesE);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑点火数据页面")
    public String edit(Long id,Model model){
        ExaminesE examinesE = examinesEService.selectById(id);
        model.addAttribute("examinesE",examinesE);
        return "/examinesE/edit";
    }

    @RequiresPermissions("scada:examinesE:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑点火数据数据")
    public RestResponse edit(ExaminesE examinesE){
        if(null == examinesE.getId() || 0 == examinesE.getId()){
            return RestResponse.failure("ID不能为空");
        }
        if(examinesE.getProduceId() == null){
            return RestResponse.failure("生产产品不能为空");
        }
        examinesEService.updateById(examinesE);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:examinesE:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除点火数据数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        ExaminesE examinesE = examinesEService.selectById(id);
        examinesE.setDelFlag(true);
        examinesEService.updateById(examinesE);
        return RestResponse.success();
    }

    @GetMapping("exportExcel")
    @ResponseBody
    @SysLog("请求列表数据,导出Excel")
    public void exportExcel(ServletRequest request, HttpServletResponse response){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        EntityWrapper<ExaminesEVO> wrapper = new EntityWrapper<>();
        wrapper.eq("examines_e.del_flag",false);
        wrapper.eq("plan.del_flag",false);
        wrapper.orderBy("produce.sn", true);
        if(!map.isEmpty()){
            String planId = (String) map.get("planId");
            if(StringUtils.isNotBlank(planId)) {
                wrapper.eq("plan.id",planId);
            }else{
                map.remove("planId");
            }
            String productType = (String) map.get("productType");
            if(StringUtils.isNotBlank(productType)) {
                wrapper.like("product.type",productType);
            }else{
                map.remove("productType");
            }
            String productSN = (String) map.get("productSn");
            if(StringUtils.isNotBlank(productSN)) {
                wrapper.like("product.sn",productSN.trim());
            }else{
                map.remove("productSN");
            }
            String produceSn = (String) map.get("produceSn");
            if(StringUtils.isNotBlank(produceSn)) {
                wrapper.like("produce.sn",produceSn.trim());
            }else{
                map.remove("produceSn");
            }

            String beginCollectDate = (String) map.get("beginCollectDate");
            String endCollectDate = (String) map.get("endCollectDate");
            if(StringUtils.isNotBlank(beginCollectDate)) {
                Date begin = DateUtil.parse(beginCollectDate);
                wrapper.ge("examines_e.collect_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginCollectDate");
            }
            if(StringUtils.isNotBlank(endCollectDate)) {
                Date end = DateUtil.parse(endCollectDate);
                wrapper.le("examines_e.collect_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endCollectDate");
            }

            String result = (String) map.get("result");
            if(StringUtils.isNotBlank(result)) {
                wrapper.like("examines_e.result",result);
            }else{
                map.remove("result");
            }
            String error = (String) map.get("error");
            if(StringUtils.isNotBlank(error)) {
                wrapper.andNew().ne("examines_e.result","PASS");
                wrapper.or().isNull("examines_e.result");
            }else{
                map.remove("error");
            }
        }
        List<ExaminesEVO> pageData = examinesEService.getAllExaminesEVOToExcal(wrapper);
        if(pageData != null && pageData.size() > 0){
            //HttpServletResponse response =  getHttpResponse();

            long t1 = System.currentTimeMillis();
            ExcelUtils.writeExcel(response, pageData, ExaminesEVO.class, "终检点火数据");
            long t2 = System.currentTimeMillis();
            String message = String.format("write over! cost:%sms", (t2 - t1));
            System.out.println(message);
        }
    }

}