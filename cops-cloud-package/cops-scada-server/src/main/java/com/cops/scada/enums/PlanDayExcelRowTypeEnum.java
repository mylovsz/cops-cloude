package com.cops.scada.enums;

import lombok.Getter;

public enum PlanDayExcelRowTypeEnum {
    None(""),PlugIn("JOB0001"), Composing("JOB0002"), Package("JOB0003"), Subsidiary("JOB0004");

    @Getter
    private String jobSn;

    private PlanDayExcelRowTypeEnum(String value){
        this.jobSn = value;
    }
}
