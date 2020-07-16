package com.cops.scada.controller;

import com.cops.scada.annotation.SysLog;
import com.cops.scada.entity.Repair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class SystemController {
    @GetMapping("notice")
    @SysLog("跳转公告页面")
    public String notice() {
        return "/system/notice";
    }

    @GetMapping("connectNC")
    @SysLog("跳转公告页面")
    public String connectNC() {
        return "/system/connectNC";
    }
}
