package com.cops.scada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.DeviceRepair;
import com.cops.scada.service.DeviceRepairService;
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
 * 设备维修  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@Controller
@RequestMapping("/deviceRepair")
public class DeviceRepairController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceRepairController.class);

    @Autowired
    private DeviceRepairService deviceRepairService;

    //@RequiresPermissions("scada:deviceRepair:list")
    @GetMapping("list")
    @SysLog("跳转设备维修列表")
    public String list(){
        return "/deviceRepair/list";
    }

    @RequiresPermissions("scada:deviceRepair:list")
    @PostMapping("list")
    @ResponseBody
    @SysLog("请求设备维修列表数据")
    public LayerData<DeviceRepair> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<DeviceRepair> layerData = new LayerData<>();
        EntityWrapper<DeviceRepair> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
        }
        Page<DeviceRepair> pageData = deviceRepairService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @RequiresPermissions("scada:deviceRepair:add")
    @GetMapping("add")
    @SysLog("跳转新增设备维修页面")
    public String add(){
        return "/deviceRepair/add";
    }

    @RequiresPermissions("scada:deviceRepair:add")
    @PostMapping("add")
    @SysLog("保存新增设备维修数据")
    @ResponseBody
    public RestResponse add(DeviceRepair deviceRepair){
        if(deviceRepair.getEnsureUserId() == null){
            return RestResponse.failure("确认人不能为空");
        }
        deviceRepairService.insert(deviceRepair);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:deviceRepair:edit")
    @GetMapping("edit")
    @SysLog("跳转编辑设备维修页面")
    public String edit(Long id,Model model){
        DeviceRepair deviceRepair = deviceRepairService.selectById(id);
        model.addAttribute("deviceRepair",deviceRepair);
        return "/deviceRepair/edit";
    }

    @RequiresPermissions("scada:deviceRepair:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑设备维修数据")
    public RestResponse edit(DeviceRepair deviceRepair){
        if(null == deviceRepair.getId() || 0 == deviceRepair.getId()){
            return RestResponse.failure("ID不能为空");
        }
        if(deviceRepair.getEnsureUserId() == null){
            return RestResponse.failure("确认人不能为空");
        }
        deviceRepairService.updateById(deviceRepair);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:deviceRepair:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除设备维修数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        DeviceRepair deviceRepair = deviceRepairService.selectById(id);
        deviceRepair.setDelFlag(true);
        deviceRepairService.updateById(deviceRepair);
        return RestResponse.success();
    }

}