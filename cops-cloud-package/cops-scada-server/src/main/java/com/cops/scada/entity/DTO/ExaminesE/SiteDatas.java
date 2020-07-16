package com.cops.scada.entity.DTO.ExaminesE;

import java.util.Date;

public class SiteDatas {

    private String Barcode;
    private String VolMax;
    private String VolValue;
    private String VolMin;
    private String TimeMax;
    private String TimeValue;
    private String TimeMin;
    private String Result;
    private Date DateTime;

    public void setBarcode(String Barcode) {
        this.Barcode = Barcode;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setVolMax(String VolMax) {
        this.VolMax = VolMax;
    }

    public String getVolMax() {
        return VolMax;
    }

    public void setVolValue(String VolValue) {
        this.VolValue = VolValue;
    }

    public String getVolValue() {
        return VolValue;
    }

    public void setVolMin(String VolMin) {
        this.VolMin = VolMin;
    }

    public String getVolMin() {
        return VolMin;
    }

    public void setTimeMax(String TimeMax) {
        this.TimeMax = TimeMax;
    }

    public String getTimeMax() {
        return TimeMax;
    }

    public void setTimeValue(String TimeValue) {
        this.TimeValue = TimeValue;
    }

    public String getTimeValue() {
        return TimeValue;
    }

    public void setTimeMin(String TimeMin) {
        this.TimeMin = TimeMin;
    }

    public String getTimeMin() {
        return TimeMin;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public String getResult() {
        return Result;
    }

    public void setDateTime(Date DateTime) {
        this.DateTime = DateTime;
    }

    public Date getDateTime() {
        return DateTime;
    }
}
