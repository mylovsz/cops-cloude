package com.cops.scada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.ExaminesCExperience;
import com.cops.scada.service.ExaminesCExperienceService;
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
 * 老化数据内容  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2020-01-03
 */
@Controller
@RequestMapping("/examinesCExperience")
public class ExaminesCExperienceController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExaminesCExperienceController.class);

    @Autowired
    private ExaminesCExperienceService examinesCExperienceService;

    @GetMapping("list")
    @SysLog("跳转老化数据内容列表")
    public String list(){
        return "/examinesCExperience/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求老化数据内容列表数据")
    public LayerData<ExaminesCExperience> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<ExaminesCExperience> layerData = new LayerData<>();
        EntityWrapper<ExaminesCExperience> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
        }
        Page<ExaminesCExperience> pageData = examinesCExperienceService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增老化数据内容页面")
    public String add(){
        return "/examinesCExperience/add";
    }

    @PostMapping("add")
    @SysLog("保存新增老化数据内容数据")
    @ResponseBody
    public RestResponse add(ExaminesCExperience examinesCExperience){
        if(examinesCExperience.getExaminesId() == null){
            return RestResponse.failure("关联id不能为空");
        }
        examinesCExperienceService.insert(examinesCExperience);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑老化数据内容页面")
    public String edit(Long id,Model model){
        ExaminesCExperience examinesCExperience = examinesCExperienceService.selectById(id);
        model.addAttribute("examinesCExperience",examinesCExperience);
        return "/examinesCExperience/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑老化数据内容数据")
    public RestResponse edit(ExaminesCExperience examinesCExperience){
        if(null == examinesCExperience.getId() || 0 == examinesCExperience.getId()){
            return RestResponse.failure("ID不能为空");
        }
        if(examinesCExperience.getExaminesId() == null){
            return RestResponse.failure("关联id不能为空");
        }
        examinesCExperienceService.updateById(examinesCExperience);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除老化数据内容数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        ExaminesCExperience examinesCExperience = examinesCExperienceService.selectById(id);
        examinesCExperience.setDelFlag(true);
        examinesCExperienceService.updateById(examinesCExperience);
        return RestResponse.success();
    }

}