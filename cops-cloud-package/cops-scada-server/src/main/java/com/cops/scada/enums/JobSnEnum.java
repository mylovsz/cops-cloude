package com.cops.scada.enums;

import lombok.Getter;

public enum JobSnEnum {
    None(""),PlugIn("JOB0001"), Composing("JOB0002"), Package("JOB0003"), Subsidiary("JOB0004");

    @Getter
    private String jobSn;

    JobSnEnum(String jobSn){
        this.jobSn = jobSn;
    }
}
