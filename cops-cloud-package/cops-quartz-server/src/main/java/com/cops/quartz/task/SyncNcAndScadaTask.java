package com.cops.quartz.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 同步NC和Scada系统数据
 */
@Component("SyncNcAndScadaTask")
public class SyncNcAndScadaTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncNcAndScadaTask.class);

    /**
     * 从NC同步订单状态到Scada中
     */
    public void NcToScadaForPlan(){
        LOGGER.debug(LocalDateTime.now().toString());
    }
}
