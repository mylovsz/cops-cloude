package com.cops.scada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.ManHour;
import com.cops.scada.service.ManHourService;
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
 * 工时管理  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@Controller
@RequestMapping("/manHour")
public class ManHourController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManHourController.class);

    @Autowired
    private ManHourService manHourService;

    @GetMapping("list")
    @SysLog("跳转工时管理列表")
    public String list(){
        return "/manHour/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求工时管理列表数据")
    public LayerData<ManHour> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<ManHour> layerData = new LayerData<>();
        EntityWrapper<ManHour> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
        }
        Page<ManHour> pageData = manHourService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增工时管理页面")
    public String add(){
        return "/manHour/add";
    }

    @PostMapping("add")
    @SysLog("保存新增工时管理数据")
    @ResponseBody
    public RestResponse add(ManHour manHour){
        manHourService.insert(manHour);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑工时管理页面")
    public String edit(Long id,Model model){
        ManHour manHour = manHourService.selectById(id);
        model.addAttribute("manHour",manHour);
        return "/manHour/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑工时管理数据")
    public RestResponse edit(ManHour manHour){
        if(null == manHour.getId() || 0 == manHour.getId()){
            return RestResponse.failure("ID不能为空");
        }
        manHourService.updateById(manHour);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除工时管理数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        ManHour manHour = manHourService.selectById(id);
        manHour.setDelFlag(true);
        manHourService.updateById(manHour);
        return RestResponse.success();
    }

}