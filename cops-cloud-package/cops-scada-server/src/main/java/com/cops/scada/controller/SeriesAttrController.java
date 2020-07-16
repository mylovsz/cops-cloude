package com.cops.scada.controller;

import com.alibaba.fastjson.JSON;
import com.cops.scada.entity.SeriesAndAttr;
import com.cops.scada.entity.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.SeriesAttr;
import com.cops.scada.service.SeriesAttrService;
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
import java.util.stream.Collectors;

import com.cops.scada.base.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

/**
 * <p>
 * 属性  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
@Controller
@RequestMapping("/seriesAttr")
public class SeriesAttrController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesAttrController.class);

    @Autowired
    private SeriesAttrService seriesAttrService;

    @GetMapping("list")
    @SysLog("跳转属性列表")
    public String list(Model model, @RequestParam(value = "typeValue",defaultValue = "1")Integer typeValue) {
        model.addAttribute("seriesTypeValue", typeValue);
        return "/seriesAttr/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求属性列表数据")
    public LayerData<SeriesAttr> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                      ServletRequest request) {
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<SeriesAttr> layerData = new LayerData<>();
        EntityWrapper<SeriesAttr> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag", false);
        if (!map.isEmpty()) {
            String name = (String) map.get("name");
            if (StringUtils.isNotBlank(name)) {
                wrapper.like("name", name);
            } else {
                map.remove("name");
            }

            String isSearch = (String) map.get("isSearch");
            if (StringUtils.isNotBlank(isSearch)) {
                wrapper.eq("is_search", isSearch);
            } else {
                map.remove("isSearch");
            }

            String type = (String) map.get("type");
            if (StringUtils.isNotBlank(type)) {
                wrapper.eq("type", type);
            } else {
                map.remove("type");
            }

        }
        Page<SeriesAttr> pageData = seriesAttrService.selectPage(new Page<>(page, limit), wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增属性页面")
    public String add(Model model, @RequestParam(value = "typeValue",defaultValue = "1")Integer typeValue) {
        model.addAttribute("seriesTypeValue", typeValue);
        return "/seriesAttr/add";
    }

    @GetMapping("cognate")
    @SysLog("跳转关联属性页面")
    public String cognate(Long id, Integer typeValue, Model model) {
        List<SeriesAndAttr> seriesAttrSelected = seriesAttrService.selectAttrIdsBySeriesId(id);
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.ne("del_flag", 1);
        entityWrapper.eq("type", typeValue);
        List<SeriesAttr> seriesAttrAll = seriesAttrService.selectList(entityWrapper);
        model.addAttribute("seriesAttrSelected", seriesAttrSelected);
        model.addAttribute("seriesAttrAll", seriesAttrAll);
        model.addAttribute("parentId", id);

        model.addAttribute("seriesTypeValue", typeValue);

        return "/seriesAttr/cognate";
    }


    @PostMapping("cognate")
    @SysLog("提交关联属性页面")
    @ResponseBody
    public RestResponse cognate(Long[] ids, Long parentId) {
        RestResponse restResponse = RestResponse.success();
        if (parentId != -1) {
            final List<SeriesAndAttr> seriesAttrSelected = seriesAttrService.selectAttrIdsBySeriesId(parentId);
            List<Long> idList = Arrays.asList(ids);
            List<SeriesAndAttr> deleteIds = seriesAttrSelected.stream().filter(s -> !idList.contains(s.getSeriesAttrId())).collect(Collectors.toList());
            List<Long> addAttrIds = idList.stream().filter(s -> !seriesAttrSelected.stream().map(SeriesAndAttr::getSeriesAttrId).collect(Collectors.toList()).contains(s)).collect(Collectors.toList());
            List<SeriesAndAttr> updateSeriesAndAttr = new ArrayList<>();
            for (int i = 0; i < ids.length; i++) {
                for (SeriesAndAttr seriesAndAttr : seriesAttrSelected) {
                    if (ids[i] == seriesAndAttr.getSeriesAttrId()) {
                        seriesAndAttr.setSort((long) i);
                        updateSeriesAndAttr.add(seriesAndAttr);
                    }
                }
            }
            if (updateSeriesAndAttr.size() > 0) {
                seriesAttrService.updateSeriesAndAttrSort(updateSeriesAndAttr);
            }
            List<SeriesAttr> result = seriesAttrService.updateAttrCognate(deleteIds, addAttrIds, idList, parentId);
            restResponse.setMessage(JSON.toJSONString(result));
        }
        return restResponse;
    }

    @GetMapping("cognate_transferTable")
    @SysLog("跳转关联属性页面")
    public String cognateT(Long id, Model model) {
        List<SeriesAndAttr> seriesAttrSelected = seriesAttrService.selectAttrIdsBySeriesId(id);
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.ne("del_flag", 1);
        List<SeriesAttr> seriesAttrAll = seriesAttrService.selectList(entityWrapper);
        model.addAttribute("seriesAttrSelected", seriesAttrSelected);
        model.addAttribute("seriesAttrAll", seriesAttrAll);
        model.addAttribute("parentId", id);
        return "/seriesAttr/cognate_transferTable";
    }


//    @PostMapping("cognate_transferTable")
//    @SysLog("提交关联属性页面")
//    @ResponseBody
//    public RestResponse cognateT(Long[] ids, Long parentId) {
//        RestResponse restResponse = RestResponse.success();
//        if (parentId != -1) {
//            final List<SeriesAndAttr> seriesAttrSelected = seriesAttrService.selectAttrIdsBySeriesId(parentId);
//            List<Long> idList = Arrays.asList(ids);
//            List<SeriesAndAttr> deleteIds = seriesAttrSelected.stream().filter(s -> !idList.contains(s.getSeriesAttrId())).collect(Collectors.toList());
//            List<Long> addAttrIds = idList.stream().filter(s -> !seriesAttrSelected.stream().map(SeriesAndAttr::getSeriesAttrId).collect(Collectors.toList()).contains(s)).collect(Collectors.toList());
//            List<SeriesAttr> result = seriesAttrService.updateAttrCognate(deleteIds, addAttrIds, parentId);
//            restResponse.setMessage(JSON.toJSONString(result));
//        }
//        return restResponse;
//    }

    @PostMapping("listtest")
    @ResponseBody
    @SysLog("请求属性列表数据")
    public LayerData<TestVO> listTest(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                      ServletRequest request) {

        LayerData<TestVO> layerData = new LayerData<>();
        List<TestVO> ts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestVO t = new TestVO();
            t.setAaa(String.valueOf(i));
            t.setBbb("name" + String.valueOf(i));
            t.setCcc(String.valueOf(i));

            ts.add(t);
        }
        layerData.setData(ts);
        layerData.setCount(10);
        return layerData;
    }


    @PostMapping("add")
    @SysLog("保存新增属性数据")
    @ResponseBody
    public RestResponse add(SeriesAttr seriesAttr) {
        if (seriesAttr.getIsSearch() == null) {
            return RestResponse.failure("是否加入到筛选不能为空");
        }
        seriesAttrService.insert(seriesAttr);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑属性页面")
    public String edit(Long id, Model model) {
        SeriesAttr seriesAttr = seriesAttrService.selectById(id);
        model.addAttribute("seriesAttr", seriesAttr);
        return "/seriesAttr/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑属性数据")
    public RestResponse edit(SeriesAttr seriesAttr) {
        if (null == seriesAttr.getId() || 0 == seriesAttr.getId()) {
            return RestResponse.failure("ID不能为空");
        }
        if (seriesAttr.getIsSearch() == null) {
            return RestResponse.failure("是否加入到筛选不能为空");
        }
        seriesAttrService.updateById(seriesAttr);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除属性数据")
    public RestResponse delete(@RequestParam(value = "id", required = false) Long id) {
        if (null == id || 0 == id) {
            return RestResponse.failure("ID不能为空");
        }
        SeriesAttr seriesAttr = seriesAttrService.selectById(id);
        seriesAttr.setDelFlag(true);
        seriesAttrService.updateById(seriesAttr);
        return RestResponse.success();
    }

}