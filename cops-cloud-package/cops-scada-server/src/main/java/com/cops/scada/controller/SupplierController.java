package com.cops.scada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.Supplier;
import com.cops.scada.service.SupplierService;
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
 * 设备供应商  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    private SupplierService supplierService;

    @RequiresPermissions("scada:supplier:list")
    @GetMapping("list")
    @SysLog("跳转设备供应商列表")
    public String list(){
        return "/supplier/list";
    }

    @RequiresPermissions("scada:supplier:list")
    @PostMapping("list")
    @ResponseBody
    @SysLog("请求设备供应商列表数据")
    public LayerData<Supplier> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Supplier> layerData = new LayerData<>();
        EntityWrapper<Supplier> wrapper = new EntityWrapper<>();
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

            String state = (String) map.get("state");
            if(StringUtils.isNotBlank(state)) {
                wrapper.eq("state",state);
            }else{
                map.remove("state");
            }

        }
        Page<Supplier> pageData = supplierService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @RequiresPermissions("scada:supplier:add")
    @GetMapping("add")
    @SysLog("跳转新增设备供应商页面")
    public String add(){
        return "supplier/add";
    }

    @RequiresPermissions("scada:supplier:add")
    @PostMapping("add")
    @SysLog("保存新增设备供应商数据")
    @ResponseBody
    public RestResponse add(Supplier supplier){
        if(StringUtils.isBlank(supplier.getSn())){
            return RestResponse.failure("供应商编号不能为空");
        }
        if(StringUtils.isBlank(supplier.getName())){
            return RestResponse.failure("供应商名称不能为空");
        }
        if(supplier.getState() == null){
            return RestResponse.failure("状态不能为空");
        }
        supplierService.insert(supplier);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:supplier:edit")
    @GetMapping("edit")
    @SysLog("跳转编辑设备供应商页面")
    public String edit(Long id,Model model){
        Supplier supplier = supplierService.selectById(id);
        model.addAttribute("supplier",supplier);
        return "/supplier/edit";
    }

    @RequiresPermissions("scada:supplier:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑设备供应商数据")
    public RestResponse edit(Supplier supplier){
        if(null == supplier.getId() || 0 == supplier.getId()){
            return RestResponse.failure("ID不能为空");
        }
        if(StringUtils.isBlank(supplier.getSn())){
            return RestResponse.failure("供应商编号不能为空");
        }
        if(StringUtils.isBlank(supplier.getName())){
            return RestResponse.failure("供应商名称不能为空");
        }
        if(supplier.getState() == null){
            return RestResponse.failure("状态不能为空");
        }
        supplierService.updateById(supplier);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:supplier:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除设备供应商数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        Supplier supplier = supplierService.selectById(id);
        supplier.setDelFlag(true);
        supplierService.updateById(supplier);
        return RestResponse.success();
    }
}