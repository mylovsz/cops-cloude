package com.cops.scada.controller;

import com.cops.scada.entity.DTO.ExaminesE.SiteInfo;
import com.cops.scada.service.impl.MemaryCacheServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.Device;
import com.cops.scada.service.DeviceService;
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
 * 设备管理  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@Controller
@RequestMapping("/device")
public class DeviceController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;

    @Autowired
    MemaryCacheServiceImpl memaryCacheService;

    //@RequiresPermissions("scada:device:list")
    @GetMapping("list")
    @SysLog("跳转设备管理列表")
    public String list(){
        return "/device/list";
    }

    @RequiresPermissions("scada:device:list")
    @PostMapping("list")
    @ResponseBody
    @SysLog("请求设备管理列表数据")
    public LayerData<Device> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Device> layerData = new LayerData<>();
        EntityWrapper<Device> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String sn = (String) map.get("sn");
            if(StringUtils.isNotBlank(sn)) {
                wrapper.like("sn",sn);
            }else{
                map.remove("sn");
            }

            String name = (String) map.get("name");
            if(StringUtils.isNotBlank(name)) {
                wrapper.like("name",name);
            }else{
                map.remove("name");
            }

            String type = (String) map.get("type");
            if(StringUtils.isNotBlank(type)) {
                wrapper.eq("type",type);
            }else{
                map.remove("type");
            }

        }
        Page<Device> pageData = deviceService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @RequiresPermissions("scada:device:add")
    @GetMapping("add")
    @SysLog("跳转新增设备管理页面")
    public String add(){
        return "/device/add";
    }

    @RequiresPermissions("scada:device:add")
    @PostMapping("add")
    @SysLog("保存新增设备管理数据")
    @ResponseBody
    public RestResponse add(Device device){
        if(StringUtils.isBlank(device.getSn())){
            return RestResponse.failure("设备编码不能为空");
        }
        if(StringUtils.isBlank(device.getName())){
            return RestResponse.failure("设备名称不能为空");
        }
        if(device.getType() == null){
            return RestResponse.failure("设备类型不能为空");
        }
        if(device.getState() == null){
            return RestResponse.failure("设备状态不能为空");
        }
        deviceService.insert(device);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:device:edit")
    @GetMapping("edit")
    @SysLog("跳转编辑设备管理页面")
    public String edit(Long id,Model model){
        Device device = deviceService.selectById(id);
        model.addAttribute("device",device);
        return "/device/edit";
    }

    @RequiresPermissions("scada:device:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑设备管理数据")
    public RestResponse edit(Device device){
        if(null == device.getId() || 0 == device.getId()){
            return RestResponse.failure("ID不能为空");
        }
        if(StringUtils.isBlank(device.getSn())){
            return RestResponse.failure("设备编码不能为空");
        }
        if(StringUtils.isBlank(device.getName())){
            return RestResponse.failure("设备名称不能为空");
        }
        if(device.getType() == null){
            return RestResponse.failure("设备类型不能为空");
        }
        if(device.getState() == null){
            return RestResponse.failure("设备状态不能为空");
        }
        deviceService.updateById(device);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:device:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除设备管理数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        Device device = deviceService.selectById(id);
        device.setDelFlag(true);
        deviceService.updateById(device);
        return RestResponse.success();
    }



    @GetMapping("statelist")
    @SysLog("跳转设备状态管理列表")
    public String statelist(){

        List<SiteInfo> siteInfos=new ArrayList<>();
        SiteInfo siteInfo=(SiteInfo)memaryCacheService.get("LUX-BJB021");
        siteInfos.add(siteInfo);

        return "/device/statelist";
    }

    //@RequiresPermissions("scada:device:list")
    @PostMapping("statelist")
    @ResponseBody
    @SysLog("跳转设备状态管理列表")
    public List<SiteInfo> statelist(ServletRequest request){
        List<SiteInfo> siteInfos=new ArrayList<>();
        SiteInfo siteInfo=(SiteInfo)memaryCacheService.get("1");
        siteInfos.add(siteInfo);
        return  siteInfos;
    }
}