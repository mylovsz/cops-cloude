package com.cops.scada.entity.DTO.ExaminesE;

import javax.xml.crypto.Data;
import java.util.Date;

public class SiteInfo {

    private String SiteWorkers;
    private String SiteLocation;
    private String SiteDataType;
    private String SiteIP;
    private Date CreateTime;

    public void setSiteWorkers(String SiteWorkers) {
        this.SiteWorkers = SiteWorkers;
    }

    public String getSiteWorkers() {
        return SiteWorkers;
    }

    public void setSiteLocation(String SiteLocation) {
        this.SiteLocation = SiteLocation;
    }

    public String getSiteLocation() {
        return SiteLocation;
    }

    public void setSiteDataType(String SiteDataType) {
        this.SiteDataType = SiteDataType;
    }

    public String getSiteDataType() {
        return SiteDataType;
    }

    public void setSiteIP(String SiteIP) {
        this.SiteIP = SiteIP;
    }

    public String getSiteIP() {
        return SiteIP;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    public Date getCreateTime() {
        return CreateTime;
    }


    @Override
    public String toString() {
        return "SiteInfo{" +
                "SiteWorkers='" + SiteWorkers + '\'' +
                ", SiteLocation='" + SiteLocation + '\'' +
                ", SiteDataType='" + SiteDataType + '\'' +
                ", SiteIP='" + SiteIP + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                '}';
    }
}
