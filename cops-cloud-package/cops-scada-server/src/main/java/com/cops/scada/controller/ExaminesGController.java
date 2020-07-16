package com.cops.scada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.ExaminesG;
import com.cops.scada.service.ExaminesGService;
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
 * 初检耐压  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2019-03-20
 */
@Controller
@RequestMapping("/examinesG")
public class ExaminesGController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExaminesGController.class);

    @Autowired
    private ExaminesGService examinesGService;

    @GetMapping("list")
    @SysLog("跳转初检耐压列表")
    public String list(){
        return "/examinesG/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求初检耐压列表数据")
    public LayerData<ExaminesG> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<ExaminesG> layerData = new LayerData<>();
        EntityWrapper<ExaminesG> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String result = (String) map.get("result");
            if(StringUtils.isNotBlank(result)) {
                wrapper.like("result",result);
            }else{
                map.remove("result");
            }

        }
        Page<ExaminesG> pageData = examinesGService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增初检耐压页面")
    public String add(){
        return "/examinesG/add";
    }

    @PostMapping("add")
    @SysLog("保存新增初检耐压数据")
    @ResponseBody
    public RestResponse add(ExaminesG examinesG){
        if(examinesG.getProduceId() == null){
            return RestResponse.failure("生产产品不能为空");
        }
        examinesGService.insert(examinesG);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑初检耐压页面")
    public String edit(Long id,Model model){
        ExaminesG examinesG = examinesGService.selectById(id);
        model.addAttribute("examinesG",examinesG);
        return "/examinesG/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑初检耐压数据")
    public RestResponse edit(ExaminesG examinesG){
        if(null == examinesG.getId() || 0 == examinesG.getId()){
            return RestResponse.failure("ID不能为空");
        }
        if(examinesG.getProduceId() == null){
            return RestResponse.failure("生产产品不能为空");
        }
        examinesGService.updateById(examinesG);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除初检耐压数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        ExaminesG examinesG = examinesGService.selectById(id);
        examinesG.setDelFlag(true);
        examinesGService.updateById(examinesG);
        return RestResponse.success();
    }

}