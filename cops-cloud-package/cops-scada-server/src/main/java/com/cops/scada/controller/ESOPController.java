package com.cops.scada.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.entity.nc65.BomOnhand;
import com.cops.entity.nc65.DTO.BomOnhandWrapperDTO;
import com.cops.entity.scada.VO.BomOnhandWrapperVO;
import com.cops.entity.scada.VO.MaterialWrapperVO;
import com.cops.library.until.DataConvert;
import com.cops.library.until.NC65RestResponse;
import com.cops.scada.annotation.SysLog;
import com.cops.scada.base.BaseController;
import com.cops.scada.entity.VO.JobVO;
import com.cops.scada.service.JobService;
import com.cops.scada.service.NC65BomService;
import com.cops.scada.util.LayerData;
import com.cops.scada.util.RestResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/esop")
public class ESOPController extends BaseController {

    @Value("${path-resource.url}")
    private String pathResourceUrl;

    @Autowired
    private JobService jobService;

    @GetMapping("index")
    @SysLog("跳转esop加载页面")
    public String index(){
        return "/ESOP/index";
    }

    @GetMapping("indexID")
    @ResponseBody
    @SysLog("校验编码")
    public RestResponse index(ServletRequest request){
        String id = request.getParameter("sn");
        if (id==null) {
            return RestResponse.success().setMessage("编号不允许为空");
        }
        if (id.length()!=12) {
            return RestResponse.success().setMessage("请输入正确的编号");
        }
        else {
            String planSn =id.substring(0,5);
            String jobSn =id.substring(5);

            String[] ss=new String[3];
            JobVO pageData = jobService.getJobVOBy(planSn,jobSn);
            ss[0]= pageData.getSopAttachment();
            ss[1]= pageData.getSopName();
            ss[2]= pathResourceUrl + pageData.getSopAttachment();

            if (ss[0]!=null) {
                return RestResponse.success().setData(ss);
                //return RestResponse.success().setMessage(sop);
            }
            else
                return RestResponse.failure("没有找到SOP文件");
        }
    }

    @GetMapping("indexpdf")
    @SysLog("跳转esop-pdf页面")
    public String index1(){
        return "/ESOP/index-pdf";
    }



    @GetMapping("indexsopfilename")
    @SysLog("跳转esopPDF页面")
    public RestResponse indexsopfilename(ServletRequest request){

        String id = request.getParameter("sn");
        String planSn =id.substring(0,5);
        String jobSn =id.substring(5);

        JobVO pageData = jobService.getJobVOBy(planSn,jobSn);
        String sopUrl=pageData.getSopAttachment();
        String sopName=pageData.getSopName();
        if (sopName!=null)
            return RestResponse.success().setMessage(sopName);
        else
            return RestResponse.failure("没有找到SOP文件");
    }
}
