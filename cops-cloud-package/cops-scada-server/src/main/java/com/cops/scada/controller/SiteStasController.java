package com.cops.scada.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.annotation.SysLog;
import com.cops.scada.base.BaseController;
import com.cops.scada.entity.Plan;
import com.cops.scada.entity.Product;
import com.cops.scada.entity.VO.SiteStasVO;
import com.cops.scada.service.SiteStasService;
import com.cops.scada.util.LayerData;
import com.xiaoleilu.hutool.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
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

@Controller
@RequestMapping("/SiteStas")
public class SiteStasController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExaminesAController.class);

    @Autowired
    private SiteStasService siteStasService;

    @GetMapping("list")
    @SysLog("站点数据")
    public String list(Model model){
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        List<Product> productList = productService.selectAll();
        model.addAttribute("productSNList", productList);
        return "/quality/SiteStas";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("站点数据")
    public LayerData<SiteStasVO> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<SiteStasVO> layerData = new LayerData<>();
        EntityWrapper<SiteStasVO> wrapper = new EntityWrapper<>();
//        wrapper.eq("examines_a.del_flag",false);
        wrapper.eq("tab1.del_flag",false);


        if(!map.isEmpty()){
            String planId = (String) map.get("planId");
            if(StringUtils.isNotBlank(planId)) {
                wrapper.eq("plan.id",planId);
            }else{
                map.remove("planId");
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
                wrapper.ge("tab1.create_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginCollectDate");
            }
            if(StringUtils.isNotBlank(endCollectDate)) {
                Date end = DateUtil.parse(endCollectDate);
                wrapper.le("tab1.create_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endCollectDate");
            }

//            String result = (String) map.get("result");
//            if(StringUtils.isNotBlank(result)) {
//                wrapper.like("examines_a.result",result);
//            }else{
//                map.remove("result");
//            }
//            String error = (String) map.get("error");
//            if(StringUtils.isNotBlank(error)) {
//                wrapper.andNew().ne("examines_a.result","PASS");
//                wrapper.or().isNull("examines_a.result");
//            }else{
//                map.remove("error");
//            }
        }
//        产品总条数
        Page<SiteStasVO> pageData = (Page<SiteStasVO>) siteStasService.getSiteStasTypeCount("scada_plan",new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());

        return layerData;
    }


    @PostMapping("list2")
    @ResponseBody
    @SysLog("站点数据")
    public LayerData<SiteStasVO> list2(@RequestParam(value = "page",defaultValue = "1")Integer page,
                        @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                        ServletRequest request,Model model){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<SiteStasVO> layerData = new LayerData<>();
        EntityWrapper<SiteStasVO> wrapper = new EntityWrapper<>();
//        wrapper.eq("examines_a.del_flag",false);
        wrapper.eq("tab1.del_flag",false);

        if(!map.isEmpty()){
            String planId = (String) map.get("planId");
            if(StringUtils.isNotBlank(planId)) {
                wrapper.eq("plan.id",planId);
            }else{
                map.remove("planId");
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
                wrapper.ge("tab1.collect_date",com.cops.scada.util.DateUtil.getStartTime(begin));
            }else{
                map.remove("beginCollectDate");
            }
            if(StringUtils.isNotBlank(endCollectDate)) {
                Date end = DateUtil.parse(endCollectDate);
                wrapper.le("tab1.collect_date",com.cops.scada.util.DateUtil.getEndTime(end));
            }else{
                map.remove("endCollectDate");
            }

//            String result = (String) map.get("result");
//            if(StringUtils.isNotBlank(result)) {
//                wrapper.like("examines_a.result",result);
//            }else{
//                map.remove("result");
//            }
//            String error = (String) map.get("error");
//            if(StringUtils.isNotBlank(error)) {
//                wrapper.andNew().ne("examines_a.result","PASS");
//                wrapper.or().isNull("examines_a.result");
//            }else{
//                map.remove("error");
//            }
        }

        List<SiteStasVO> ALLlist=new ArrayList<>();
        ALLlist.add(SiteStasVOInit("初检耐压","scada_examines_a",wrapper));
        ALLlist.add(SiteStasVOInit("初检","scada_examines_b",wrapper));
        ALLlist.add(SiteStasVOInit("老化","scada_examines_c",wrapper));
        ALLlist.add(SiteStasVOInit("终检耐压","scada_examines_d",wrapper));
        ALLlist.add(SiteStasVOInit("点火","scada_examines_e",wrapper));
        ALLlist.add(SiteStasVOInit("终检","scada_examines_f",wrapper));

        //model.addAttribute("ALLlist", ALLlist);

        layerData.setData(ALLlist);
        layerData.setCount(ALLlist.size());
        return layerData;
    }


    public SiteStasVO SiteStasVOInit(String name,String tabName,EntityWrapper<SiteStasVO> wrapper)
    {
        SiteStasVO siteStasVO=new SiteStasVO();
        siteStasVO.setValue1(siteStasService.getSiteStasTypeCount2(tabName,wrapper).get(0).getValue1());          //        产品总条数
        siteStasVO.setValue1(siteStasService.getSiteStasALLTestRecordCount(tabName,wrapper).get(0).getValue1());  //        测试总条数
        siteStasVO.setValue2(siteStasService.getSiteStasTestOKRecordCount(tabName,wrapper).get(0).getValue1());   //        正常个数
        siteStasVO.setValue3(siteStasService.getSiteStasTestFailRecordCount(tabName,wrapper).get(0).getValue1()); //        异常个数
        siteStasVO.setValue4(siteStasService.getSiteStasTestRedoRecordCount(tabName,wrapper).get(0).getValue1()); //        重复测试2次以上个数
        siteStasVO.setValue5(name);
        siteStasVO.setValue6(siteStasService.getSiteStasTypeCount2(tabName,wrapper).get(0).getValue1());  //        产品总条数
        return siteStasVO;
    }
}
