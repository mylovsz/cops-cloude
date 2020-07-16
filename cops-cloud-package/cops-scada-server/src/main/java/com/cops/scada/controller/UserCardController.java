package com.cops.scada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.UserCard;
import com.cops.scada.service.UserCardService;
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
 * 工卡信息  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2020-04-08
 */
@Controller
@RequestMapping("/userCard")
public class UserCardController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCardController.class);

    @Autowired
    private UserCardService userCardService;

    @GetMapping("list")
    @SysLog("跳转工卡信息列表")
    public String list(){
        return "/userCard/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求工卡信息列表数据")
    public LayerData<UserCard> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<UserCard> layerData = new LayerData<>();
        EntityWrapper<UserCard> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String type = (String) map.get("type");
            if(StringUtils.isNotBlank(type)) {
                wrapper.like("type",type);
            }else{
                map.remove("type");
            }

            String beginJoinDate = (String) map.get("beginJoinDate");
            String endJoinDate = (String) map.get("endJoinDate");
            if(StringUtils.isNotBlank(beginJoinDate)) {
                Date begin = DateUtil.parse(beginJoinDate);
                wrapper.ge("join_date",begin);
            }else{
                map.remove("beginJoinDate");
            }
            if(StringUtils.isNotBlank(endJoinDate)) {
                Date end = DateUtil.parse(endJoinDate);
                wrapper.le("join_date",end);
            }else{
                map.remove("endJoinDate");
            }

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

            String department = (String) map.get("department");
            if(StringUtils.isNotBlank(department)) {
                wrapper.like("department",department);
            }else{
                map.remove("department");
            }

            String duty = (String) map.get("duty");
            if(StringUtils.isNotBlank(duty)) {
                wrapper.like("duty",duty);
            }else{
                map.remove("duty");
            }

            String cardSn = (String) map.get("cardSn");
            if(StringUtils.isNotBlank(cardSn)) {
                wrapper.like("card_sn",cardSn);
            }else{
                map.remove("cardSn");
            }

            String beginCreateCardDate = (String) map.get("beginCreateCardDate");
            String endCreateCardDate = (String) map.get("endCreateCardDate");
            if(StringUtils.isNotBlank(beginCreateCardDate)) {
                Date begin = DateUtil.parse(beginCreateCardDate);
                wrapper.ge("create_card_date",begin);
            }else{
                map.remove("beginCreateCardDate");
            }
            if(StringUtils.isNotBlank(endCreateCardDate)) {
                Date end = DateUtil.parse(endCreateCardDate);
                wrapper.le("create_card_date",end);
            }else{
                map.remove("endCreateCardDate");
            }

        }
        Page<UserCard> pageData = userCardService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增工卡信息页面")
    public String add(){
        return "/userCard/add";
    }

    @PostMapping("add")
    @SysLog("保存新增工卡信息数据")
    @ResponseBody
    public RestResponse add(UserCard userCard){
        userCardService.insert(userCard);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑工卡信息页面")
    public String edit(Long id,Model model){
        UserCard userCard = userCardService.selectById(id);
        model.addAttribute("userCard",userCard);
        return "/userCard/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑工卡信息数据")
    public RestResponse edit(UserCard userCard){
        if(null == userCard.getId() || 0 == userCard.getId()){
            return RestResponse.failure("ID不能为空");
        }
        userCardService.updateById(userCard);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除工卡信息数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        UserCard userCard = userCardService.selectById(id);
        userCard.setDelFlag(true);
        userCardService.updateById(userCard);
        return RestResponse.success();
    }

}