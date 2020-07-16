package com.cops.scada.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.annotation.SysLog;
import com.cops.scada.base.BaseController;
import com.cops.scada.entity.Plan;
import com.cops.scada.entity.Produce;
import com.cops.scada.entity.Product;
import com.cops.scada.entity.VO.PlanProductVO;
import com.cops.scada.entity.VO.PlanQualityDetailVO;
import com.cops.scada.entity.VO.PlanQualityVO;
import com.cops.scada.service.PlanQualityService;
import com.cops.scada.service.PlanService;
import com.cops.scada.service.ProduceService;
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
 * 品质日报  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-03-14
 */
@Controller
@RequestMapping("/quality")
public class QualityController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QualityController.class);

    @Autowired
    private PlanService planService;

    @Autowired
    private PlanQualityService planQualityService;

    @GetMapping("collect")
    @SysLog("跳转汇总报表")
    public String collect(Model model) {
        return "/quality/collect";
    }

    @GetMapping("detail")
    @SysLog("跳转品质详情")
    public String detail(Long planId,Model model){
        PlanQualityDetailVO planQualityDetailVO = planService.getPlanQualityDetailVO(planId);
        model.addAttribute("planQualityDetailVO",planQualityDetailVO);
        return "/quality/listDetail";
    }

    @GetMapping("list")
    @SysLog("跳转品质日报列表")
    public String list(Model model) {
        List<Product> products = productService.selectAll();
        model.addAttribute("productList", products);
        List<Plan> planList = planService.getAllPlan();
        model.addAttribute("planList", planList);
        return "/quality/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求品质日报列表数据")
    public LayerData<PlanQualityVO> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                           ServletRequest request) {
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<PlanQualityVO> layerData = new LayerData<>();
        EntityWrapper<PlanQualityVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan.del_flag", false);
        if (!map.isEmpty()) {

            String planType = (String) map.get("planType");
            if (StringUtils.isNotBlank(planType)) {
                wrapper.eq("plan.type", planType.trim());
            } else {
                map.remove("planType");
            }

            String planId = (String) map.get("planId");
            if (StringUtils.isNotBlank(planId)) {
                wrapper.eq("plan.id", planId.trim());
            } else {
                map.remove("planId");
            }

            String productType = (String) map.get("productType");
            if(StringUtils.isNotBlank(productType)) {
                wrapper.like("product.type",productType);
            }else{
                map.remove("productType");
            }

            String productId = (String) map.get("productId");
            if (StringUtils.isNotBlank(productId)) {
                wrapper.eq("product.id", productId);
            } else {
                map.remove("productId");
            }

            String beginCollectDate = (String) map.get("beginCollectDate");
            String endCollectDate = (String) map.get("endCollectDate");
            if (StringUtils.isNotBlank(beginCollectDate)) {
                Date begin = DateUtil.parse(beginCollectDate);
                wrapper.ge("plan.tag_start_date", com.cops.scada.util.DateUtil.getStartTime(begin));
            } else {
                map.remove("beginCollectDate");
            }
            if (StringUtils.isNotBlank(endCollectDate)) {
                Date end = DateUtil.parse(endCollectDate);
                wrapper.le("plan.tag_start_date", com.cops.scada.util.DateUtil.getEndTime(end));
            } else {
                map.remove("endCollectDate");
            }

            String state = (String) map.get("state");
            if (StringUtils.isNotBlank(state)) {
                wrapper.eq("plan.state", state);
            } else {
                map.remove("state");
            }
        }
        Page<PlanQualityVO> pageData = planQualityService.getPlanQualityVO(new Page<>(page, limit), wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }
}