package com.cops.scada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.DeviceCheck;
import com.cops.scada.service.DeviceCheckService;
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
 * 设备校检  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@Controller
@RequestMapping("/deviceCheck")
public class DeviceCheckController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceCheckController.class);

    @Autowired
    private DeviceCheckService deviceCheckService;

    //@RequiresPermissions("scada:deviceCheck:list")
    @GetMapping("list")
    @SysLog("跳转设备校检列表")
    public String list(){
        return "/deviceCheck/list";
    }

    @RequiresPermissions("scada:deviceCheck:list")
    @PostMapping("list")
    @ResponseBody
    @SysLog("请求设备校检列表数据")
    public LayerData<DeviceCheck> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<DeviceCheck> layerData = new LayerData<>();
        EntityWrapper<DeviceCheck> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String sn = (String) map.get("sn");
            if(StringUtils.isNotBlank(sn)) {
                wrapper.like("sn",sn);
            }else{
                map.remove("sn");
            }

            String type = (String) map.get("type");
            if(StringUtils.isNotBlank(type)) {
                wrapper.eq("type",type);
            }else{
                map.remove("type");
            }

        }
        Page<DeviceCheck> pageData = deviceCheckService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @RequiresPermissions("scada:deviceCheck:add")
    @GetMapping("add")
    @SysLog("跳转新增设备校检页面")
    public String add(){
        return "/deviceCheck/add";
    }

    @RequiresPermissions("scada:deviceCheck:add")
    @PostMapping("add")
    @SysLog("保存新增设备校检数据")
    @ResponseBody
    public RestResponse add(DeviceCheck deviceCheck){
        if(StringUtils.isBlank(deviceCheck.getSn())){
            return RestResponse.failure("例检编号不能为空");
        }
        if(deviceCheck.getType() == null){
            return RestResponse.failure("类型不能为空");
        }
        deviceCheckService.insert(deviceCheck);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:deviceCheck:edit")
    @GetMapping("edit")
    @SysLog("跳转编辑设备校检页面")
    public String edit(Long id,Model model){
        DeviceCheck deviceCheck = deviceCheckService.selectById(id);
        model.addAttribute("deviceCheck",deviceCheck);
        return "/deviceCheck/edit";
    }

    @RequiresPermissions("scada:deviceCheck:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑设备校检数据")
    public RestResponse edit(DeviceCheck deviceCheck){
        if(null == deviceCheck.getId() || 0 == deviceCheck.getId()){
            return RestResponse.failure("ID不能为空");
        }
        if(StringUtils.isBlank(deviceCheck.getSn())){
            return RestResponse.failure("例检编号不能为空");
        }
        if(deviceCheck.getType() == null){
            return RestResponse.failure("类型不能为空");
        }
        deviceCheckService.updateById(deviceCheck);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:deviceCheck:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除设备校检数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        DeviceCheck deviceCheck = deviceCheckService.selectById(id);
        deviceCheck.setDelFlag(true);
        deviceCheckService.updateById(deviceCheck);
        return RestResponse.success();
    }

}