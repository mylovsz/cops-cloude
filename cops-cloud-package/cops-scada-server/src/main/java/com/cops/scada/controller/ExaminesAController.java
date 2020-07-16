package com.cops.scada.controller;

import com.cops.scada.entity.Plan;
import com.cops.scada.entity.Product;
import com.cops.scada.entity.VO.ExaminesAVO;
import com.cops.scada.entity.VO.ExaminesTestTime;
import com.cops.scada.entity.VO.QualityExaminesVO;
import com.cops.scada.service.*;
import com.cops.scada.util.ExcelUtils;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.ExaminesA;
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
import com.cops.library.until.DateConvert;

import java.util.*;

import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.util.RestResponse;
import com.cops.scada.base.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.util.WebUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
/**
 * <p>
 * 初检耐压  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-03-22
 */
@Controller
@RequestMapping("/examinesA")
public class ExaminesAController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExaminesAController.class);

    @Autowired
    private ExaminesAService examinesAService;

    @Autowired
    private ExaminesBService examinesBService;

    @Autowired
    private ExaminesCService examinesCService;

    @Autowired
    private ExaminesDService examinesDService;

    @Autowired
    private ExaminesFService examinesFService;

    @Autowired
    private ExaminesGService examinesGService;

    @PostMapping("collect")
    @ResponseBody
    public RestResponse collect(
          @RequestParam(value = "collectDate") String collectDate
        , @RequestParam(value = "timeMin")Long timeMin
        , @RequestParam(value = "timeMax")Long timeMax
        , @RequestParam(value = "type")Integer type){
        if(collectDate == null || collectDate.length() == 0)
            return RestResponse.failure("请选择时间");

        Calendar c = Calendar.getInstance();
        EntityWrapper<ExaminesTestTime> wrapper = new EntityWrapper<>();
        c.setTime(DateUtil.parseDate(collectDate));
        c.set(Calendar.AM_PM, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        wrapper.ge("examines.collect_date", c.getTime());
        c.add(Calendar.DATE, 1);
        wrapper.lt("examines.collect_date", c.getTime());

        List<ExaminesTestTime> list;
        List<Long> pvList = Lists.newArrayList();
        //List<Long> idList = Lists.newArrayList();
        List<String> idList = Lists.newArrayList();
        RestResponse rr = RestResponse.success();

        switch (type){
            case 1:
                rr.setAny("type", "初检耐压");
                list = examinesAService.getExaminesTestTime(wrapper);
                break;
            case 2:
                rr.setAny("type", "初检");
                list = examinesBService.getExaminesTestTime(wrapper);
                break;
            case 3:
                rr.setAny("type", "老化");
                list = examinesCService.getExaminesTestTime(wrapper);
                break;
            case 4:
                rr.setAny("type", "终检耐压");
                list = examinesDService.getExaminesTestTime(wrapper);
                break;
            case 5:
                rr.setAny("type", "点火");
                list = examinesEService.getExaminesTestTime(wrapper);
                break;
            case 6:
                rr.setAny("type", "终检");
                list = examinesFService.getExaminesTestTime(wrapper);
                break;
            case 7:
                rr.setAny("type", "包装");
                list = examinesGService.getExaminesTestTime(wrapper);
                break;
            default:
                rr.setAny("type", "初检耐压");
                list = examinesAService.getExaminesTestTime(wrapper);
                break;
        }
        for (int i=0;i<list.size();i++){
            Long testTime = list.get(i).getTestTime();
            if(testTime == null || testTime > timeMax || testTime < timeMin)
                continue;
            //idList.add(list.get(i).getId());
            idList.add(DateUtil.formatTime(list.get(i).getCollectDate()));

            pvList.add(list.get(i).getTestTime());
        }
        rr.setAny("collectA", pvList);
        rr.setAny("id", idList);
        return rr;
    }

    @RequiresPermissions("scada:examinesA:removeDup")
    @PostMapping("removeDup")
    @ResponseBody
    @SysLog("去除重复数据")
    public RestResponse removeDup(String productSN){
        Long count = examinesAService.deleteDup();
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

        Page<QualityExaminesVO> qe = examinesAService.getQulityDay1(new Page<QualityExaminesVO>(page, limit),start,end );
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

        List<QualityExaminesVO> qe = examinesAService.getQulityDayKanban(start, end);
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
    @SysLog("请求当日质量数据")
    public LayerData<QualityExaminesVO> qualityDay(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "limit", defaultValue = "10") Integer limit
                                                   ){
        LayerData<QualityExaminesVO> layerData = new LayerData<>();

        Date start = DateConvert.getStartTime(new Date());
        Date end = DateConvert.getEndTime(new Date());

        Page<QualityExaminesVO> qe = examinesAService.getQulityDay1(new Page<QualityExaminesVO>(page, limit),start,end );
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

        Page<QualityExaminesVO> qe = examinesAService.getQulityDay3(new Page<QualityExaminesVO>(page, limit),start,end,wrapper );
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
        return "/examinesA/list";
    }

    @RequiresPermissions("scada:examinesA:list")
    @PostMapping("list")
    @ResponseBody
    @SysLog("请求初检耐压列表数据")
    public LayerData<ExaminesAVO> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<ExaminesAVO> layerData = new LayerData<>();
        EntityWrapper<ExaminesAVO> wrapper = new EntityWrapper<>();
        wrapper.eq("examines_a.del_flag",false);
        wrapper.eq("plan.del_flag",false);
        if(!map.isEmpty()){
            String planId = (String) map.get("planId");
            if(StringUtils.isNotBlank(planId)) {
                wrapper.eq("plan.id",Integer.parseInt(planId));
            }else{
                map.remove("planId");
            }

            String productType = (String) map.get("productType");
            if(StringUtils.isNotBlank(productType)) {
                wrapper.eq("product.type",Integer.parseInt(productType));
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
                wrapper.ge("examines_a.collect_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginCollectDate");
            }
            if(StringUtils.isNotBlank(endCollectDate)) {
                Date end = DateUtil.parse(endCollectDate);
                wrapper.le("examines_a.collect_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endCollectDate");
            }

            String result = (String) map.get("result");
            if(StringUtils.isNotBlank(result)) {
                wrapper.like("examines_a.result",result);
            }else{
                map.remove("result");
            }
            String error = (String) map.get("error");
            if(StringUtils.isNotBlank(error)) {
                wrapper.andNew().ne("examines_a.result","PASS");
                wrapper.or().isNull("examines_a.result");
            }else{
                map.remove("error");
            }
        }
        Page<ExaminesAVO> pageData = examinesAService.getExaminesAVO(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增初检耐压页面")
    public String add(){
        return "/examinesA/add";
    }

    @RequiresPermissions("scada:examinesA:add")
    @PostMapping("add")
    @SysLog("保存新增初检耐压数据")
    @ResponseBody
    public RestResponse add(ExaminesA examinesA){
        if(examinesA.getProduceId() == null){
            return RestResponse.failure("生产产品不能为空");
        }
        examinesAService.insert(examinesA);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑初检耐压页面")
    public String edit(Long id,Model model){
        ExaminesA examinesA = examinesAService.selectById(id);
        model.addAttribute("examinesA",examinesA);
        return "/examinesA/edit";
    }

    @RequiresPermissions("scada:examinesA:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑初检耐压数据")
    public RestResponse edit(ExaminesA examinesA){
        if(null == examinesA.getId() || 0 == examinesA.getId()){
            return RestResponse.failure("ID不能为空");
        }
        if(examinesA.getProduceId() == null){
            return RestResponse.failure("生产产品不能为空");
        }
        examinesAService.updateById(examinesA);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:examinesA:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除初检耐压数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        ExaminesA examinesA = examinesAService.selectById(id);
        examinesA.setDelFlag(true);
        examinesAService.updateById(examinesA);
        return RestResponse.success();
    }


    @GetMapping("exportExcel")
    @ResponseBody
    @SysLog("请求列表数据,导出Excel")
    public void exportExcel(ServletRequest request, HttpServletResponse response){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        EntityWrapper<ExaminesAVO> wrapper = new EntityWrapper<>();
        wrapper.eq("examines_a.del_flag",false);
        wrapper.eq("plan.del_flag",false);
        if(!map.isEmpty()){
            String planId = (String) map.get("planId");
            if(StringUtils.isNotBlank(planId)) {
                wrapper.eq("plan.id",Integer.parseInt(planId));
            }else{
                map.remove("planId");
            }
            String productType = (String) map.get("productType");
            if(StringUtils.isNotBlank(productType)) {
                wrapper.eq("product.type",Integer.parseInt(productType));
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
                wrapper.ge("examines_a.collect_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginCollectDate");
            }
            if(StringUtils.isNotBlank(endCollectDate)) {
                Date end = DateUtil.parse(endCollectDate);
                wrapper.le("examines_a.collect_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endCollectDate");
            }

            String result = (String) map.get("result");
            if(StringUtils.isNotBlank(result)) {
                wrapper.like("examines_a.result",result);
            }else{
                map.remove("result");
            }
            String error = (String) map.get("error");
            if(StringUtils.isNotBlank(error)) {
                wrapper.andNew().ne("examines_a.result","PASS");
                wrapper.or().isNull("examines_a.result");
            }else{
                map.remove("error");
            }
        }
        List<ExaminesAVO> pageData = examinesAService.getAllExaminesAVOToExcal(wrapper);
        if(pageData != null && pageData.size() > 0){
            //HttpServletResponse response =  getHttpResponse();

            long t1 = System.currentTimeMillis();
            ExcelUtils.writeExcel(response, pageData, ExaminesAVO.class, "初检耐压数据");
            long t2 = System.currentTimeMillis();
            String message = String.format("write over! cost:%sms", (t2 - t1));
            System.out.println(message);
        }
    }
}