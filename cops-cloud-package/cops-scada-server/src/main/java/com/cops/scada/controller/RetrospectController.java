package com.cops.scada.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.annotation.SysLog;
import com.cops.scada.entity.VO.*;
import com.cops.scada.service.*;
import com.cops.scada.util.LayerData;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/retrospect")
public class RetrospectController {

    @Autowired
    private ExaminesAService examinesAService;
    @Autowired
    private ExaminesBService examinesBService;
    @Autowired
    private ExaminesCService examinesCService;
    @Autowired
    private ExaminesDService examinesDService;
    @Autowired
    private ExaminesEService examinesEService;
    @Autowired
    private ExaminesFService examinesFService;

    @GetMapping("product")
    @SysLog("跳转产品追溯页面")
    public String retrospect() {
        return "/retrospect/retrospect";
    }

    @PostMapping("product")
    @ResponseBody
    @SysLog("获取产品追溯数据")
    public Object searchProduct(ServletRequest request) {
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        if (!map.isEmpty()) {

            String sn = (String) map.get("sn");
            EntityWrapper<ExaminesAVO> ExaminesAVOWrapper = new EntityWrapper<>();
            EntityWrapper<ExaminesBVO> ExaminesBVOWrapper = new EntityWrapper<>();
            EntityWrapper<ExaminesCVO> ExaminesCVOWrapper = new EntityWrapper<>();
            EntityWrapper<ExaminesDVO> ExaminesDVOWrapper = new EntityWrapper<>();
            EntityWrapper<ExaminesEVO> ExaminesEVOWrapper = new EntityWrapper<>();
            EntityWrapper<ExaminesFVO> ExaminesFVOWrapper = new EntityWrapper<>();

            ExaminesAVOWrapper.eq("examines_a.del_flag", false);
            ExaminesAVOWrapper.eq("plan.del_flag", false);

            ExaminesBVOWrapper.eq("examines_b.del_flag", false);
            ExaminesBVOWrapper.eq("plan.del_flag", false);

            ExaminesCVOWrapper.eq("examines_c.del_flag", false);
            ExaminesCVOWrapper.eq("plan.del_flag", false);

            ExaminesDVOWrapper.eq("examines_d.del_flag", false);
            ExaminesDVOWrapper.eq("plan.del_flag", false);

            ExaminesEVOWrapper.eq("examines_e.del_flag", false);
            ExaminesEVOWrapper.eq("plan.del_flag", false);

            ExaminesFVOWrapper.eq("examines_f.del_flag", false);
            ExaminesFVOWrapper.eq("plan.del_flag", false);

            if (StringUtils.isNotBlank(sn)) {

                ExaminesAVOWrapper.eq("produce.sn", sn.trim());
                ExaminesBVOWrapper.eq("produce.sn", sn.trim());
                ExaminesCVOWrapper.eq("produce.sn", sn.trim());
                ExaminesDVOWrapper.eq("produce.sn", sn.trim());
                ExaminesEVOWrapper.eq("produce.sn", sn.trim());
                ExaminesFVOWrapper.eq("produce.sn", sn.trim());

                Page<ExaminesAVO> pageDataA = examinesAService.getExaminesAVO(new Page<>(1, 30), ExaminesAVOWrapper);
                Page<ExaminesBVO> pageDataB = examinesBService.getExaminesBVO(new Page<>(1, 30), ExaminesBVOWrapper);
                Page<ExaminesCVO> pageDataC = examinesCService.getExaminesCVO(new Page<>(1, 30), ExaminesCVOWrapper);
                Page<ExaminesDVO> pageDataD = examinesDService.getExaminesDVO(new Page<>(1, 30), ExaminesDVOWrapper);
                Page<ExaminesEVO> pageDataE = examinesEService.getExaminesEVO(new Page<>(1, 30), ExaminesEVOWrapper);
                Page<ExaminesFVO> pageDataF = examinesFService.getExaminesFVO(new Page<>(1, 30), ExaminesFVOWrapper);

                List<Object> objList = new ArrayList<>();
                objList.add(pageDataA);
                objList.add(pageDataB);
                objList.add(pageDataC);
                objList.add(pageDataD);
                objList.add(pageDataE);
                objList.add(pageDataF);

                return objList;
            }

            return "";
        }
        return "";
    }


}
