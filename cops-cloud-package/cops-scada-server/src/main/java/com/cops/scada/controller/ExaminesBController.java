package com.cops.scada.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.library.until.DateConvert;
import com.cops.scada.annotation.SysLog;
import com.cops.scada.base.BaseController;
import com.cops.scada.entity.ExaminesB;
import com.cops.scada.entity.Plan;
import com.cops.scada.entity.Product;
import com.cops.scada.entity.VO.ExaminesBVO;
import com.cops.scada.entity.VO.QualityExaminesVO;
import com.cops.scada.service.ExaminesBService;
import com.cops.scada.util.ExcelUtils;
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
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * <p>
 * 初检耐压  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-03-20
 */
@Controller
@RequestMapping("/examinesB")
public class ExaminesBController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExaminesBController.class);

    @Autowired
    private ExaminesBService examinesBService;

    @RequiresPermissions("scada:examinesB:removeDup")
    @PostMapping("removeDup")
    @ResponseBody
    @SysLog("去除重复数据")
    public RestResponse removeDup(String productSN){
        Long count = examinesBService.deleteDup();
        return RestResponse.success().setMessage("删除重复数据："+count);
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

        Page<QualityExaminesVO> qe = examinesBService.getQulityDay1(new Page<QualityExaminesVO>(page, limit) , start,end);
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

        List<QualityExaminesVO> qe = examinesBService.getQulityDayKanban(start, end);
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
    public LayerData<QualityExaminesVO> qualityDay(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        LayerData<QualityExaminesVO> layerData = new LayerData<>();

        Date start = DateConvert.getStartTime(new Date());
        Date end = DateConvert.getEndTime(new Date());

        Page<QualityExaminesVO> qe = examinesBService.getQulityDay1(new Page<QualityExaminesVO>(page, limit) , start,end);
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

        Page<QualityExaminesVO> qe = examinesBService.getQulityDay3(new Page<QualityExaminesVO>(page, limit),start,end,wrapper );
        layerData.setData(qe.getRecords());
        layerData.setCount(qe.getTotal());
        return layerData;
    }
    @GetMapping("list")
    @SysLog("跳转初检耐压列表")
    public String list(Model model){
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        List<Product> productList = productService.selectAll();
        model.addAttribute("productSNList", productList);
        return "/examinesB/list";
    }

    @RequiresPermissions("scada:examinesB:list")
    @PostMapping("list")
    @ResponseBody
    @SysLog("请求初检耐压列表数据")
    public LayerData<ExaminesBVO> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<ExaminesBVO> layerData = new LayerData<>();
        EntityWrapper<ExaminesBVO> wrapper = new EntityWrapper<>();
        wrapper.eq("examines_b.del_flag",false);
        wrapper.eq("plan.del_flag",false);
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
                wrapper.ge("examines_b.collect_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginCollectDate");
            }
            if(StringUtils.isNotBlank(endCollectDate)) {
                Date end = DateUtil.parse(endCollectDate);
                wrapper.le("examines_b.collect_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endCollectDate");
            }

            String result = (String) map.get("result");
            if(StringUtils.isNotBlank(result)) {
                wrapper.like("examines_b.result",result);
            }else{
                map.remove("result");
            }
            String error = (String) map.get("error");
            if(StringUtils.isNotBlank(error)) {
                wrapper.andNew().ne("examines_b.result","PASS");
                wrapper.or().isNull("examines_b.result");
            }else{
                map.remove("error");
            }
        }
        Page<ExaminesBVO> pageData = examinesBService.getExaminesBVO(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增初检耐压页面")
    public String add(){
        return "/examinesB/add";
    }

    @RequiresPermissions("scada:examinesB:add")
    @PostMapping("add")
    @SysLog("保存新增初检耐压数据")
    @ResponseBody
    public RestResponse add(ExaminesB examinesB){
        if(examinesB.getProduceId() == null){
            return RestResponse.failure("生产产品不能为空");
        }
        examinesBService.insert(examinesB);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑初检耐压页面")
    public String edit(Long id,Model model){
        ExaminesB examinesB = examinesBService.selectById(id);
        model.addAttribute("examinesB",examinesB);
        return "/examinesB/edit";
    }

    @RequiresPermissions("scada:examinesB:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑初检耐压数据")
    public RestResponse edit(ExaminesB examinesB){
        if(null == examinesB.getId() || 0 == examinesB.getId()){
            return RestResponse.failure("ID不能为空");
        }
        if(examinesB.getProduceId() == null){
            return RestResponse.failure("生产产品不能为空");
        }
        examinesBService.updateById(examinesB);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:examinesB:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除初检耐压数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        ExaminesB examinesB = examinesBService.selectById(id);
        examinesB.setDelFlag(true);
        examinesBService.updateById(examinesB);
        return RestResponse.success();
    }


    @GetMapping("exportExcel")
    @ResponseBody
    @SysLog("请求列表数据,导出Excel")
    public void exportExcel(ServletRequest request, HttpServletResponse response){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        EntityWrapper<ExaminesBVO> wrapper = new EntityWrapper<>();
        wrapper.eq("examines_b.del_flag",false);
        wrapper.eq("plan.del_flag",false);
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
                wrapper.ge("examines_b.collect_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginCollectDate");
            }
            if(StringUtils.isNotBlank(endCollectDate)) {
                Date end = DateUtil.parse(endCollectDate);
                wrapper.le("examines_b.collect_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endCollectDate");
            }

            String result = (String) map.get("result");
            if(StringUtils.isNotBlank(result)) {
                wrapper.like("examines_b.result",result);
            }else{
                map.remove("result");
            }
            String error = (String) map.get("error");
            if(StringUtils.isNotBlank(error)) {
                wrapper.andNew().ne("examines_b.result","PASS");
                wrapper.or().isNull("examines_b.result");
            }else{
                map.remove("error");
            }
        }
        List<ExaminesBVO> pageData = examinesBService.getAllExaminesBVOToExcal(wrapper);
        if(pageData != null && pageData.size() > 0){
            //HttpServletResponse response =  getHttpResponse();

            long t1 = System.currentTimeMillis();
            ExcelUtils.writeExcel(response, pageData, ExaminesBVO.class, "初检数据");
            long t2 = System.currentTimeMillis();
            String message = String.format("write over! cost:%sms", (t2 - t1));
            System.out.println(message);
        }
    }
}