package com.cops.scada.component;

import com.cops.scada.entity.DTO.ExaminesC.Models.ExperienceLED;
import com.cops.scada.entity.ExaminesC;
import com.cops.scada.entity.ExaminesCExperience;
import com.cops.scada.entity.Plan;
import com.cops.scada.entity.Produce;
import com.cops.scada.service.ExaminesCExperienceService;
import com.cops.scada.service.ExaminesCService;
import com.cops.scada.service.ProduceService;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AsyncTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTask.class);

    @Autowired
    private ProduceService produceService;

    @Autowired
    private ExaminesCService examinesCService;

    @Autowired
    private ExaminesCExperienceService examinesCExperienceService;

    /**
     * 异步调用存储接口
     */
    @Async
    public void ExaminesCInsertAsync(ExaminesCExperience examinesCExperience, ExperienceLED experienceLED, Plan plan, Date createDate) {
        if (StringUtils.isNotBlank(examinesCExperience.getMeterE())) {
            examinesCExperienceService.insert(examinesCExperience);
        }
        for (ExperienceLED.DataListBean dataListBean : experienceLED.getDataList()) {
            try {
                if (dataListBean.getState().equals("--")) {
                    continue;
                }
                int index = 0;
                String devNumber = dataListBean.getDeviceNumber();
                long produceId = 0;
                Produce produce = produceService.getOneBySN(devNumber);
                if (produce == null) {
                    produce = produceService.getOneByPlanSN("C", plan.getId());
                    if (produce == null) {
                        continue;
                    }
                    produceId = produce.getId();
                } else {
                    produceId = produce.getId();
                }
                if (dataListBean.getState().toUpperCase().equals("PASS")) {
                    produce.setResultC(1);
                } else {
                    produce.setResultC(2);
                }
                if (dataListBean.getDetailList() != null && dataListBean.getDetailList().size() > 0) {
                    List<ExaminesC> examinesCS = new ArrayList<>();
                    for (ExperienceLED.DataListBean.DetailListBean detail : dataListBean.getDetailList()) {
                        ExaminesC examinesC = new ExaminesC();
                        examinesC.setProduceId(produceId);
                        examinesC.setMeterN(index + "");
                        examinesC.setMeterO(examinesCExperience.getId().toString());
                        if (StringUtils.isNotBlank(dataListBean.getFailTime()) && dataListBean.getFailTime().toUpperCase().equals(detail.getTime().toUpperCase())) {
                            examinesC.setResult(dataListBean.getState());
                        } else {
                            if (detail.getVoltage().equals("--") || detail.getCurrent().equals("--")) {
                                examinesC.setResult(dataListBean.getState());
                            } else {
                                examinesC.setResult("PASS");
                            }
                        }
                        Date dt = DateUtils.addMinutes(createDate, ++index);
                        examinesC.setCollectDate(dt);
                        examinesC.setMeterA(detail.getTemperature());
                        examinesC.setMeterB(detail.getInport());
                        examinesC.setMeterC(detail.getVoltage());
                        examinesC.setMeterL(detail.getCurrent());
                        examinesCS.add(examinesC);
                    }
                    if (examinesCS.size() > 0) {
                        if (produce.getState() == 0) {
                            produce.setState(1);
                        }
                        produceService.saveProduce(produce);
                        examinesCService.insertBatch(examinesCS);
                    }
                }
            } catch (Exception e) {
                LOGGER.error("ExaminesCInsertAsync", e);
            }
        }
    }

}
