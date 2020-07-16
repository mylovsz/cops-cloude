package com.cops.scada.controller;

import com.alibaba.fastjson.JSON;
import com.cops.library.until.DateConvert;
import com.cops.scada.entity.Dict;
import com.cops.scada.entity.VO.KanBan.Show.Content;
import com.cops.scada.entity.VO.KanBan.ShowConfig.H2;
import com.cops.scada.entity.VO.KanBan.ShowConfig.Root;
import com.cops.scada.entity.VO.KanBan.ShowConfig.Style;
import com.cops.scada.entity.VO.PlanDayVO;
import com.cops.scada.entity.VO.WorkDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cops.scada.entity.Kanban;
import com.cops.scada.service.KanbanService;
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

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.util.RestResponse;
import com.cops.scada.base.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.util.WebUtils;
import com.cops.scada.entity.VO.KanBan.OrderPlanDay.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

/**
 * <p>
 * 看板配置  前端控制器
 * </p>
 *
 * @author wanglm
 * @since 2020-04-09
 */
@Controller
@RequestMapping("/kanban")
public class KanbanController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(KanbanController.class);

    @Autowired
    private KanbanService kanbanService;

    @GetMapping("list")
    @SysLog("跳转看板配置列表")
    public String list() {
        return "/kanban/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求看板配置列表数据")
    public LayerData<Kanban> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                  ServletRequest request) {
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Kanban> layerData = new LayerData<>();
        EntityWrapper<Kanban> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag", false);
        if (!map.isEmpty()) {
            String name = (String) map.get("name");
            if (StringUtils.isNotBlank(name)) {
                wrapper.like("name", name);
            } else {
                map.remove("name");
            }

        }
        Page<Kanban> pageData = kanbanService.selectPage(new Page<>(page, limit), wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    //废弃
    @GetMapping("listcard")
    @SysLog("跳转看板配置列表")
    public String listcard() {
        return "/kanban/list_card";
    }

    //废弃
    @PostMapping("listcard")
    @ResponseBody
    @SysLog("请求看板配置列表数据")
    public LayerData<Kanban> listcard(ServletRequest request) {

        LayerData<Kanban> layerData = new LayerData<>();
        EntityWrapper<Kanban> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag", false);

        List<Kanban> pageData = kanbanService.selectList(wrapper);
        layerData.setData(pageData);
        layerData.setCount(pageData.size());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增看板配置页面")
    public String add() {
        return "/kanban/add";
    }


    @PostMapping("add")
    @SysLog("保存新增看板配置数据")
    @ResponseBody
    public RestResponse add(Kanban kanban) {
        if (StringUtils.isBlank(kanban.getName())) {
            return RestResponse.failure("看板名称不能为空");
        }
        kanbanService.insert(kanban);
        return RestResponse.success();
    }


    @GetMapping("edit")
    @SysLog("跳转编辑看板配置页面")
    public String edit(Long id, Model model) {
        Kanban kanban = kanbanService.selectById(id);
        model.addAttribute("kanban", kanban);
        return "/kanban/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑看板配置数据")
    public RestResponse edit(Kanban kanban) {
        if (null == kanban.getId() || 0 == kanban.getId()) {
            return RestResponse.failure("ID不能为空");
        }
        if (StringUtils.isBlank(kanban.getName())) {
            return RestResponse.failure("看板名称不能为空");
        }
        kanbanService.updateById(kanban);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除看板配置数据")
    public RestResponse delete(@RequestParam(value = "id", required = false) Long id) {
        if (null == id || 0 == id) {
            return RestResponse.failure("ID不能为空");
        }
        Kanban kanban = kanbanService.selectById(id);
        kanban.setDelFlag(true);
        kanbanService.updateById(kanban);
        return RestResponse.success();
    }

    //自定义看板接口

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/getservertime")
    @ResponseBody
    @SysLog("获取服务器时间")
    public RestResponse getservertime(HttpServletResponse resp) {
        resp.setContentType("text/html;charset=utf-8");//解决乱码问题
        Date day = new Date();
        System.out.println(df.format(day));
        return RestResponse.success(df.format(day));
    }

    @GetMapping("showid")
    @SysLog("跳转show看板配置页面")
    public String showtest(Long id, Model model) {
        id = 1l;
        Kanban kanban = kanbanService.selectById(id);
        Root kbs = JSON.parseObject(kanban.getContent(), Root.class);
        model.addAttribute("kbs", kbs);
        return "/kanban/show";
    }

    @GetMapping("configid")
    @SysLog("跳转show看板配置页面")
    public String configtest(Long id, Model model) {
        id = 1l;
        Kanban kanban = kanbanService.selectById(id);
        Root kbs = JSON.parseObject(kanban.getContent(), Root.class);
        model.addAttribute("kbs", kbs);
        String json = JSONObject.toJSONString(kanban);
        model.addAttribute("kanbans", json);
        return "/kanban/config";
    }

    @GetMapping("show")
    @SysLog("跳转show看板配置页面")
    public String show(String name, Model model) {
        model.addAttribute("name", name);
        if (name != null && name != "") {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", name);
            List<Kanban> kanbans = kanbanService.selectByMap(map);
            if (kanbans.size() > 0) {
                Root kbs = JSON.parseObject(kanbans.get(0).getContent(), Root.class);
                model.addAttribute("kbs", kbs);
                model.addAttribute("kanbans", kanbans.get(0).getContent());


            }
        }
        return "/kanban/show";
    }

    @GetMapping("showPlanDay")
    public String board(Model model){

        Dict dict = dictService.selectById(393);
        model.addAttribute("time",dict);
        return "/kanban/showPlanDay";
    }

    @GetMapping("showIndex")
    public String showIndex(Model model) {
        return "/kanban/showIndex";
    }

    @GetMapping("showProduce")
    @SysLog("跳转show看板配置页面")
    public String showProduce(Model model) {
        return "/kanban/showProduce";
    }

    @GetMapping("config")
    @SysLog("跳转show看板配置页面")
    public String config(String name, Model model) {
        if (name != null && name != "") {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", name);
            List<Kanban> kanbans = kanbanService.selectByMap(map);
            if (kanbans.size() > 0) {
                Root kbs = JSON.parseObject(kanbans.get(0).getContent(), Root.class);
                model.addAttribute("kbs", kbs);
                String json = JSONObject.toJSONString(kanbans.get(0));
                model.addAttribute("kanbans", json);
            }
        }
        return "/kanban/config";
    }

    @RequestMapping(value = "/saveconfig", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    @SysLog("保存编辑看板配置数据")
    public RestResponse edit(@RequestBody JSONObject jsonParam) {
        try {
            Kanban kanban = JSONObject.toJavaObject(jsonParam, Kanban.class);
            kanbanService.updateById(kanban);
            return RestResponse.success();
        } catch (Exception ex) {
            return RestResponse.failure(ex.getMessage());
        }
    }


    /**
     * 使用反射根据属性名称获取属性值
     *
     * @param fieldName 属性名称
     * @param o         操作对象
     * @return Object 属性值
     */

    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            System.out.println("属性不存在");
            return null;
        }
    }


    //region 今日排产计划看板
    @PostMapping("OrderPlanDayTitleP")
    @ResponseBody
    @SysLog("请求日计划副标题")
    public LayerData<String> OrderPlanDayTitleP(ServletRequest request) {
        String name = request.getParameter("name");
        ;
        LayerData<String> layerData = new LayerData<>();
        if (name != null && name != "") {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", name);
            List<Kanban> kanbans = kanbanService.selectByMap(map);
            if (kanbans.size() > 0) {
                Root kbs = JSON.parseObject(kanbans.get(0).getContent(), Root.class);
                H2 h2 = kbs.getH2();
                List<Style> titlep = h2.getPp();
                List<String> titleps = new ArrayList<>();

                //从数据库获取副标题数据 就是某个对象返回类型，这里的titleP是测试用，最后需要删除
                EntityWrapper<WorkDetailVO> wrapper = new EntityWrapper<>();
                wrapper.eq("workDetail.del_flag", false);

                Long a = new Date().getTime() - 2 * 24 * 60 * 60 * 1000;
                Date day = new Date(a);

                //Date day=new Date();
                wrapper.between("workDetail.start_date", DateConvert.getStartTime(day), DateConvert.getEndTime(day));

                OrderPlanWHTotal total = new OrderPlanWHTotal();
                List<WorkDetailVO> orderPlanDayWHVOS = kanbanService.getWorkDetailVO(wrapper);
                for (int i = 0; i < orderPlanDayWHVOS.size(); i++) {
                    WorkDetailVO detailVO = orderPlanDayWHVOS.get(i);

                    Long chanchugongshi = 0L;//产出工时
                    switch (detailVO.getState()) {//根据状态 计算工时
                        case 0://加工中
                            Date now = new Date();
                            //((当前时间-加工开始时间)+补偿时间)* 加工人数
                            chanchugongshi = ((now.getTime() - detailVO.getStartDate().getTime()) + detailVO.getReimbursedTime()) * detailVO.getStartWorkUserNum();
                            break;
                        case 1://已完成 加工有效时长*加工人数
                            chanchugongshi = detailVO.getWorkProcessTime() * detailVO.getStartWorkUserNum();
                            break;
                    }
                    total.setWHShould(0 + 0);
                    total.setWHReal(0 + 0);
                    total.setWHBorrow(0 + 0);
                    total.setWHLend(0 + 0);
                    total.setWHInvestTotal(0 + 0);
                    total.setWHErrorTotal(0 + 0);
                    total.setProlificacyTotal(0 + 0);
                    total.setProlificacyTotalRate(0 + 0);
                }


                for (int i = 0; i < titlep.size(); i++) {
                    if (titlep.get(i).getName() != null && !titlep.get(i).getName().equals("") && titlep.get(i).getContent() != null && !titlep.get(i).getContent().equals("")
                    ) {
                        String str = titlep.get(i).getContent() + ": " + getFieldValueByName(titlep.get(i).getName(), total);//查找副标题设置的字段，找到数据库对象匹配的属性
                        titleps.add(str);
                    }
                }
                layerData.setData(titleps);
            }
        }
        return layerData;
    }

    @PostMapping("OrderPlanDayWHlist")
    @ResponseBody
    @SysLog("请求日计划工时列表数据")
    public LayerData<Content> OrderPlanDayWHlist(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                     @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                                     ServletRequest request) {
        LayerData<Content> layerData = new LayerData<>();
        EntityWrapper<PlanDayVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan_day.del_flag",false);
        wrapper.orderBy("plan_day.manufacture_date", false)
                .orderBy("master_plan_sn")
                .orderBy("job.name")
                .orderBy("type")
                .orderBy("plan.sn");

        //Long a = new Date().getTime() - 3 * 24 * 60 * 60 * 1000;
        //Date day = new Date(a);
        Date day = new Date();//当前时间
        wrapper.between("plan_day.manufacture_date", DateConvert.getStartTime(day), DateConvert.getEndTime(day));

        //数据库捞数据
        Page<PlanDayVO> pageData = kanbanService.getPagePlanDayVO(new Page<>(page, limit), wrapper);
        List<PlanDayVO> result = pageData.getRecords();

        List<Content> contents = new ArrayList<>();
        for (PlanDayVO vo : result) {

            //加工数据库数据
            String[] res=new String[10];//结果数据
            res[0]=vo.getMasterPlanSn();
            res[1]=vo.getJobName();
            res[2]=vo.getProductSn();
            res[3]=vo.getManufactureStaffs();
            res[4]=vo.getWorkTime()==null?"0":vo.getWorkTime().toString();
            res[5]=vo.getManufactureTimes()==null?"0":vo.getManufactureTimes().toString();
            res[6]="";
            res[7]="";
            res[8]="";
            res[9]="";


            //加工后数据汇总
            Content content = new Content();
            content.setStr0(res[0]);
            content.setStr1(res[1]);
            content.setStr2(res[2]);
            content.setStr3(res[3]);
            content.setStr4(res[4]);
            content.setStr5(res[5]);
            content.setStr6(res[6]);
            content.setStr7(res[7]);
            content.setStr8(res[8]);
            content.setStr9(res[9]);
            contents.add(content);
        }

        layerData.setData(contents);
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    public static final int MONEY_POINT = 4; // 货币保留两位小数

    @PostMapping("OrderPlanDaylist")
    @ResponseBody
    @SysLog("请求日计划订单列表数据")
    public LayerData<Content> OrderPlanDaylist(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                    @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                                    ServletRequest request) {

        LayerData<Content> layerData = new LayerData<>();
        EntityWrapper<PlanDayVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan_day.del_flag",false);
        wrapper.orderBy("plan_day.manufacture_date", false)
                .orderBy("master_plan_sn")
                .orderBy("job.name")
                .orderBy("type")
                .orderBy("plan.sn");

        //Long a = new Date().getTime() - 3 * 24 * 60 * 60 * 1000;
        //Date day = new Date(a);
        Date day = new Date();//当前时间
        wrapper.between("plan_day.manufacture_date", DateConvert.getStartTime(day), DateConvert.getEndTime(day));

        //数据库捞数据
        Page<PlanDayVO> pageData = kanbanService.getPagePlanDayVO(new Page<>(page, limit), wrapper);
        List<PlanDayVO> result = pageData.getRecords();

        List<Content> contents = new ArrayList<>();
        for (PlanDayVO vo : result) {

            BigDecimal quantity=new BigDecimal(vo.getPlanQuantity()==null||vo.getPlanQuantity()==""?"0":vo.getPlanQuantity());
            BigDecimal plannum=new BigDecimal(vo.getPlanNum()==null||vo.getPlanQuantity()==""?"0":vo.getPlanNum().toString());
            BigDecimal planrate=plannum.divide(quantity, MONEY_POINT, RoundingMode.HALF_UP).setScale(2,RoundingMode.HALF_UP);//订单完成率

            BigDecimal num=new BigDecimal(vo.getNum()==null?"0":vo.getNum().toString());
            BigDecimal mnum=new BigDecimal(vo.getManufactureNum()==null?"0":vo.getManufactureNum().toString());
            BigDecimal daynum=mnum;//当日进度
            if (daynum.compareTo(num)==-1){
                daynum=num;
            }
            BigDecimal errorrate=new BigDecimal(0);//不良率

            String state = "";
            switch (vo.getState()) {
                case -1:
                    state = "<span style='color:yellow;'>待产</span>";
                    break;
                case 0:
                    state = "<span style='color:#06f106;'>加工中</span>";
                    break;
                case 1:
                    state = "<span style='color:#06f106;'>完成</span>";
                    break;
                case 2:
                    state = "<span style='color:red;'>延期</span>";
                    break;
                case 3:
                    state = "<span style='color:red;'>暂停</span>";
                    break;
                case 4:
                    state = "<span style='color:yellow;'>挂起</span>";
                    break;
                default:
                    break;
            }
            //加工数据库数据
            String[] res=new String[10];//结果数据
            res[0]=vo.getMasterPlanSn();
            res[1]=vo.getJobName();
            res[2]=vo.getProductSn();
            res[3]=(planrate.doubleValue()*100)+"%";//vo.getPlanQuantity();
            res[4]=vo.getPlanNum()==null?"0":vo.getPlanNum().toString();
            res[5]=daynum.toString();///vo.getManufactureNum()==null?"0":vo.getManufactureNum().toString();
            res[6]=(errorrate.doubleValue()*100)+"%";//vo.getNum()==null?"0":vo.getNum().toString();
            res[7]="";//vo.getNumSuccess()==null?"0":vo.getNumSuccess().toString();
            res[8]="";
            res[9]=state;


            //加工后数据汇总
            Content content = new Content();
            content.setStr0(res[0]);
            content.setStr1(res[1]);
            content.setStr2(res[2]);
            content.setStr3(res[3]);
            content.setStr4(res[4]);
            content.setStr5(res[5]);
            content.setStr6(res[6]);
            content.setStr7(res[7]);
            content.setStr8(res[8]);
            content.setStr9(res[9]);
            contents.add(content);
        }

        layerData.setData(contents);
        layerData.setCount(pageData.getTotal());
        return layerData;
    }
//endregion 今日排产计划
}