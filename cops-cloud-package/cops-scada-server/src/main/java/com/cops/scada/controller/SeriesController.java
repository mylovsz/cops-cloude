package com.cops.scada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.Series;
import com.cops.scada.service.SeriesService;
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
 * 系列  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
@Controller
@RequestMapping("/series")
public class SeriesController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesController.class);

    @Autowired
    private SeriesService seriesService;

    @GetMapping("list")
    @SysLog("跳转系列列表")
    public String list(Model model, @RequestParam(value = "typeValue",defaultValue = "1")Integer typeValue){
        //return "/series/list";
        model.addAttribute("seriesTypeValue", typeValue);
        return "/series/list_transferTable";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求系列列表数据")
    public LayerData<Series> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Series> layerData = new LayerData<>();
        EntityWrapper<Series> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String name = (String) map.get("name");
            if(StringUtils.isNotBlank(name)) {
                wrapper.like("name",name);
            }else{
                map.remove("name");
            }

            String typeString = (String) map.get("typeString");
            if(StringUtils.isNotBlank(typeString)) {
                wrapper.like("type_string",typeString);
            }else{
                map.remove("typeString");
            }

            String type = (String) map.get("type");
            if(StringUtils.isNotBlank(type)) {
                wrapper.eq("type",type);
            }else{
                map.remove("type");
            }

        }
        Page<Series> pageData = seriesService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增系列页面")
    public String add(Model model, @RequestParam(value = "typeValue",defaultValue = "1")Integer typeValue){
        model.addAttribute("seriesTypeValue", typeValue);
        return "/series/add";
    }

    @PostMapping("add")
    @SysLog("保存新增系列数据")
    @ResponseBody
    public RestResponse add(Series series){
        seriesService.insert(series);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑系列页面")
    public String edit(Long id,Model model){
        Series series = seriesService.selectById(id);
        model.addAttribute("series",series);
        return "/series/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑系列数据")
    public RestResponse edit(Series series){
        if(null == series.getId() || 0 == series.getId()){
            return RestResponse.failure("ID不能为空");
        }
        seriesService.updateById(series);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除系列数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        Series series = seriesService.selectById(id);
        series.setDelFlag(true);
        seriesService.updateById(series);
        return RestResponse.success();
    }

}