package com.cops.scada.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.FactorySite;
import com.cops.scada.service.FactorySiteService;
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
 * 车间站点信息  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-04-15
 */
@Controller
@RequestMapping("/factorySite")
public class FactorySiteController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FactorySiteController.class);

    @Autowired
    private FactorySiteService factorySiteService;

    @GetMapping("list")
    @SysLog("跳转车间站点信息列表")
    public String list(){
        return "/factorySite/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求车间站点信息列表数据")
    public RestResponse list(ServletRequest request){
        List<FactorySite> pageData = factorySiteService.getListForTree();
        return RestResponse.success().setData(pageData);
    }

    @RequiresPermissions("scada:factorySite:add")
    @GetMapping("add")
    @SysLog("跳转新增车间站点信息页面")
    public String add(@RequestParam(value="parentId", required = false)Long parentId, Model model){
        if(parentId != null && parentId != 0){
            FactorySite fs = factorySiteService.selectById(parentId);
            model.addAttribute("parentFactorySite", fs);
        }
        return "/factorySite/add";
    }

    @RequiresPermissions("scada:factorySite:add")
    @PostMapping("add")
    @SysLog("保存新增车间站点信息数据")
    @ResponseBody
    public RestResponse add(FactorySite factorySite){
        if(StringUtils.isBlank(factorySite.getSn())){
            return RestResponse.failure("编码不能为空");
        }
        if(StringUtils.isBlank(factorySite.getName())){
            return RestResponse.failure("名称不能为空");
        }
        if(factorySite.getType() == null){
            return RestResponse.failure("类型不能为空");
        }

        if(factorySite.getParentId() == null){
            factorySite.setLevel(1);
            Object o = factorySiteService.selectObj(Condition.create()
                .setSqlSelect("max(sort)").isNull("parent_id")
                .eq("del_flag", false));
            int sort = 0;
            if(o != null){
                sort = (Integer)o+10;
            }
            factorySite.setSort(sort);
        }
        else {
            FactorySite fs = factorySiteService.selectById(factorySite.getParentId());
            if(fs == null){
                return RestResponse.failure("父数据不存在");
            }
            factorySite.setParentIds(fs.getParentIds());
            factorySite.setLevel(fs.getLevel()+1);
            Object o = factorySiteService.selectObj(Condition.create()
                .setSqlSelect("max(sort)")
                .eq("parent_id", factorySite.getParentId())
                .eq("del_flag", false));
            int sort = 0;
            if(o != null){
                sort = (Integer)o + 10;
            }
            factorySite.setSort(sort);
        }

        factorySiteService.saveOrUpdateChannel(factorySite);
        factorySite.setParentIds(StringUtils.isBlank(factorySite.getParentIds())?factorySite.getId()+",":factorySite.getParentIds()+factorySite.getId()+",");
        factorySiteService.saveOrUpdateChannel(factorySite);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:factorySite:add")
    @GetMapping("edit")
    @SysLog("跳转编辑车间站点信息页面")
    public String edit(Long id,Model model){
        FactorySite factorySite = factorySiteService.selectById(id);
        model.addAttribute("factorySite",factorySite);
        return "/factorySite/edit";
    }

    @RequiresPermissions("scada:factorySite:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑车间站点信息数据")
    public RestResponse edit(FactorySite factorySite){
        if(null == factorySite.getId() || 0 == factorySite.getId()){
            return RestResponse.failure("ID不能为空");
        }
        if(StringUtils.isBlank(factorySite.getSn())){
            return RestResponse.failure("编码不能为空");
        }
        if(StringUtils.isBlank(factorySite.getName())){
            return RestResponse.failure("名称不能为空");
        }
        if(factorySite.getType() == null){
            return RestResponse.failure("类型不能为空");
        }
        factorySiteService.saveOrUpdateChannel(factorySite);
        return RestResponse.success();
    }

    @RequiresPermissions("scada:factorySite:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除车间站点信息数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        FactorySite factorySite = factorySiteService.selectById(id);
        factorySite.setDelFlag(true);
        factorySiteService.saveOrUpdateChannel(factorySite);
        return RestResponse.success();
    }

}