package com.cops.scada.controller;

import com.cops.scada.entity.ProductInfo;
import com.cops.scada.entity.SeriesAttr;
import com.cops.scada.entity.VO.ProductInfoAttrValueVO;
import com.cops.scada.service.ProductInfoService;
import com.cops.scada.service.SeriesAttrService;
import com.cops.scada.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.ProductInfoAttrValue;
import com.cops.scada.service.ProductInfoAttrValueService;
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

import java.util.ArrayList;
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
 * 展示产品属性值  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
@Controller
@RequestMapping("/productInfoAttrValue")
public class ProductInfoAttrValueController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductInfoAttrValueController.class);

    @Autowired
    private ProductInfoAttrValueService productInfoAttrValueService;
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private SeriesAttrService seriesAttrService;

    @GetMapping("list")
    @SysLog("跳转展示产品属性值列表")
    public String list() {
        return "/productInfoAttrValue/list";
    }

    @GetMapping("editAttrs")
    @SysLog("跳转展示产品属性值列表")
    public String editAttrs(Long id, Model model) {
        ProductInfo productInfo = productInfoService.selectById(id);
        if (productInfo == null) {
            return "参数异常，找不到产品";
        }
        model.addAttribute("productInfo", productInfo);

        List<SeriesAttr> seriesAttrs = seriesAttrService.selectBySeriesId(productInfo.getSeriesId());
        List<Object> objects = new ArrayList<>();

        for (SeriesAttr seriesAttr : seriesAttrs) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("seriesAttr", seriesAttr);
            ProductInfoAttrValue productInfoAttrValue = productInfoAttrValueService.getProductInfoAttrByProductInfoIdAndAttrId(productInfo.getId(), seriesAttr.getId());
            jsonObject.put("productInfoAttrValue", productInfoAttrValue);
            objects.add(jsonObject);
        }

        model.addAttribute("attrs", objects);

        return "/productInfoAttrValue/editAttrs";
    }

    @PostMapping("editAttrs")
    @SysLog("跳转展示产品属性值列表")
    @ResponseBody
    public RestResponse editAttrs(ProductInfoAttrValueVO productInfoAttrValue) {
        ProductInfo productInfo = productInfoService.selectById(productInfoAttrValue.getId());
        if (productInfo == null) {
            return RestResponse.failure("未找到产品");
        }
        if (productInfoAttrValue.getProductInfoAttrValue() != null) {
            productInfoAttrValueService.insertOrUpdateBatch(productInfoAttrValue.getProductInfoAttrValue());
        }
        return RestResponse.success();
    }


    @PostMapping("list")
    @ResponseBody
    @SysLog("请求展示产品属性值列表数据")
    public LayerData<ProductInfoAttrValue> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                                ServletRequest request) {
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<ProductInfoAttrValue> layerData = new LayerData<>();
        EntityWrapper<ProductInfoAttrValue> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag", false);
        if (!map.isEmpty()) {
        }
        Page<ProductInfoAttrValue> pageData = productInfoAttrValueService.selectPage(new Page<>(page, limit), wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增展示产品属性值页面")
    public String add() {
        return "/productInfoAttrValue/add";
    }

    @PostMapping("add")
    @SysLog("保存新增展示产品属性值数据")
    @ResponseBody
    public RestResponse add(ProductInfoAttrValue productInfoAttrValue) {
        if (productInfoAttrValue.getProductInfoId() == null) {
            return RestResponse.failure("展示产品关联不能为空");
        }
        if (productInfoAttrValue.getSeriesAttrId() == null) {
            return RestResponse.failure("属性关联id不能为空");
        }
        productInfoAttrValueService.insert(productInfoAttrValue);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑展示产品属性值页面")
    public String edit(Long id, Model model) {
        ProductInfoAttrValue productInfoAttrValue = productInfoAttrValueService.selectById(id);
        model.addAttribute("productInfoAttrValue", productInfoAttrValue);
        return "/productInfoAttrValue/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑展示产品属性值数据")
    public RestResponse edit(ProductInfoAttrValue productInfoAttrValue) {
        if (null == productInfoAttrValue.getId() || 0 == productInfoAttrValue.getId()) {
            return RestResponse.failure("ID不能为空");
        }
        if (productInfoAttrValue.getProductInfoId() == null) {
            return RestResponse.failure("展示产品关联不能为空");
        }
        if (productInfoAttrValue.getSeriesAttrId() == null) {
            return RestResponse.failure("属性关联id不能为空");
        }
        productInfoAttrValueService.updateById(productInfoAttrValue);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除展示产品属性值数据")
    public RestResponse delete(@RequestParam(value = "id", required = false) Long id) {
        if (null == id || 0 == id) {
            return RestResponse.failure("ID不能为空");
        }
        ProductInfoAttrValue productInfoAttrValue = productInfoAttrValueService.selectById(id);
        productInfoAttrValue.setDelFlag(true);
        productInfoAttrValueService.updateById(productInfoAttrValue);
        return RestResponse.success();
    }

}