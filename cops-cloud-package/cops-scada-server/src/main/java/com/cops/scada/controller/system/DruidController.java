package com.cops.scada.controller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/druid")
public class DruidController {

    @RequiresPermissions("sys:druid:list")
    @GetMapping("list")
    public String index(){
        return "admin/system/druid/index";
    }
}
