package com.cops.scada.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.scada.component.AsyncTask;
import com.cops.scada.entity.*;
import com.cops.scada.entity.DTO.ExaminesC.Models.ExperienceLED;
import com.cops.scada.entity.VO.Result;
import com.cops.scada.service.*;
import com.cops.scada.util.RestResponse;
import lombok.experimental.var;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.ThreadContext;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.cops.scada.util.ToolUtil.LOGGER;

@Service
public class WebSocketServiceImpl implements WebSocketService {

    @Autowired
    private ExaminesCExperienceService examinesCExperienceService;

    @Autowired
    protected PlanService planService;

    @Autowired
    private ProduceService produceService;

    @Autowired
    private AsyncTask asyncTask;

    @Autowired
    private DeviceService deviceService;

    @Override
    public RestResponse doWebsocketData(String message, String deviceId) {
        RestResponse response = RestResponse.success();
        try {
            JSONObject base = JSONObject.parseObject(message);
            long id = base.getLong("id");
            String body = base.getString("body");
            JSONObject jsonObject = JSONObject.parseObject(body);
            response.put("id", id);
            if (jsonObject.containsKey("upload_type")) {
                response.put("type", "upload");
                switch (jsonObject.getString("upload_type")) {
                    case "led_examines_c":
                        if (led_examines_c(response, jsonObject)) return response;
                        break;
                    default:
                        response.setSuccess(false);
                        response.setMessage("数据格式错误");
                        return response;
                }
            } else if (jsonObject.containsKey("register")) {

                String sn = "";
                String name = "";
                String deviceType = "";
                String specifications = "";

                if (jsonObject.containsKey("sn")) {
                    sn = jsonObject.getString("sn");
                }
                if (jsonObject.containsKey("name")) {
                    name = jsonObject.getString("name");
                }
                if (jsonObject.containsKey("deviceType")) {
                    deviceType = jsonObject.getString("deviceType");
                }
                if (jsonObject.containsKey("specifications")) {
                    specifications = jsonObject.getString("specifications");
                }

                EntityWrapper<Device> entityWrapper = new EntityWrapper();
                entityWrapper.eq("sn", sn);
                Device device = deviceService.selectOne(entityWrapper);

                if (device == null) {
                    device = new Device();
                    device.setSn(sn);
                    device.setName(name);
                    device.setType(Integer.parseInt(deviceType));
                    device.setSpec(specifications);
                    device.setState(5);
                    deviceService.insert(device);
                }

            }
        } catch (Exception e) {
            LOGGER.error("WebSocket", e);
        }
        response.setSuccess(false);
        response.put("id", -99);
        return response;
    }

    private boolean led_examines_c(RestResponse response, JSONObject jsonObject) throws ParseException {
        //判断是否存在value
        if (jsonObject.containsKey("value")) {
            //转换为实体
            ExperienceLED experienceLED = JSON.parseObject(jsonObject.getString("value"), ExperienceLED.class);//JSON.toJavaObject(jsonObject, ExperienceLED.class);
            if (experienceLED != null) {
                EntityWrapper<ExaminesCExperience> wrapper = new EntityWrapper<>();
                String meterA = jsonObject.getString("name").replace(".xls", "");
                wrapper.eq("meter_a", meterA);
                if (examinesCExperienceService.selectCount(wrapper) > 0) {
                    response.setSuccess(false);
                    response.setMessage("异常数据，重复提交");
                    return true;
                }
                if (Integer.parseInt(experienceLED.getTotal()) == 0) {
                    response.setSuccess(false);
                    response.setMessage("异常数据，不处理");
                    return true;
                }
                ExaminesCExperience examinesCExperience = new ExaminesCExperience();
                examinesCExperience.setMeterA(meterA);
                if (experienceLED.getCreateTime().toLowerCase().indexOf("by") != -1) {
                    examinesCExperience.setMeterB(experienceLED.getCreateTime().toLowerCase().substring(0, experienceLED.getCreateTime().toLowerCase().indexOf("by") - 1));
                } else {
                    examinesCExperience.setMeterB(experienceLED.getCreateTime());
                }
                examinesCExperience.setMeterC(experienceLED.getPass());
                examinesCExperience.setMeterD(experienceLED.getFailedPercent());

                Plan plan = new Plan();
                if (StringUtils.isNotBlank(experienceLED.getOrderNumber())) {
                    examinesCExperience.setMeterE(experienceLED.getOrderNumber());
                    EntityWrapper<Plan> planEntityWrapper = new EntityWrapper<>();
                    planEntityWrapper.eq("sn", experienceLED.getOrderNumber());
                    plan = planService.selectOne(planEntityWrapper);
                    if (plan == null) {
                        response.setSuccess(false);
                        response.setMessage("异常数据，不处理");
                        return true;
                    }
                } else {
                    List<ExperienceLED.DataListBean> lst = experienceLED.getDataList().stream().filter(t -> t.getDeviceNumber() != null && t.getDeviceNumber().length() > 0).collect(Collectors.toList());
                    if (lst.size() > 0) {
                        String sn = lst.get(0).getDeviceNumber();
                        Produce produce = produceService.getOneBySN(sn);
                        if (produce != null) {
                            plan.setId(produce.getPlanId());
                            plan = planService.selectById(plan);
                            if (plan != null) {
                                examinesCExperience.setMeterE(plan.getSn());
                            } else {
                                response.setSuccess(false);
                                response.setMessage("无匹配数据，不处理");
                                return true;
                            }
                        } else {
                            response.setSuccess(false);
                            response.setMessage("无匹配数据，不处理");
                            return true;
                        }
                    } else {
                        response.setSuccess(false);
                        response.setMessage("无匹配数据，不处理");
                        return true;
                    }
                    lst.clear();
                }
                examinesCExperience.setMeterF(experienceLED.getFailed());
                examinesCExperience.setMeterG(experienceLED.getTotal());
                examinesCExperience.setMeterH(experienceLED.getDeviceModel());
                String[] pattern = new String[]{"yyyy-MM", "yyyyMM", "yyyy/MM",
                        "yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd",
                        "yyyyMMddHHmmss",
                        "yyyy-MM-dd HH:mm:ss",
                        "yyyy/MM/dd HH:mm:ss"};
                Date createDate = DateUtils.parseDate(examinesCExperience.getMeterB(), pattern);

                long currentTimeMillis = System.currentTimeMillis();
                if (experienceLED.getDataList() != null && experienceLED.getDataList().size() > 0) {
                    asyncTask.ExaminesCInsertAsync(examinesCExperience, experienceLED, plan, createDate);
                }
                long currentTimeMillis1 = System.currentTimeMillis();
                response.setMessage("处理成功," + "task任务总耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
                return true;
            }
        }
        return false;
    }


    @Override
    public RestResponse doWebsocketData(WebsocketMessage websocketMessage, String deviceId) {
        return null;
    }

    @Override
    public Device checkDevice(String deviceId) {
        if (StringUtils.isNotBlank(deviceId)) {
            EntityWrapper<Device> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("sn", deviceId);
            Device device = deviceService.selectOne(entityWrapper);
            if (device != null) {
                return device;
            }
        }
        return null;
    }

    @Override
    public void updateDeviceState(Device device) {
        deviceService.updateById(device);
    }
}
