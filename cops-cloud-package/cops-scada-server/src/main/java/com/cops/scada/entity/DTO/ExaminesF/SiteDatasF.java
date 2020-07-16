package com.cops.scada.entity.DTO.ExaminesF;

import java.util.Date;

public class SiteDatasF {
    private String ProductModel;
    private String JobNumber;
    private Date DateTime;
    private String Barcode;

    private String GNDResult;
    private String ACWResult;
    private String Voltage;
    private String PowerFactor;
    private String Power1;
    private String Power2;
    private String Power3;
    private String Power4;
    private String Result;
    public void setProductModel(String ProductModel) {
        this.ProductModel = ProductModel;
    }
    public String getProductModel() {
        return ProductModel;
    }

    public void setJobNumber(String JobNumber) {
        this.JobNumber = JobNumber;
    }
    public String getJobNumber() {
        return JobNumber;
    }

    public void setDateTime(Date DateTime) {
        this.DateTime = DateTime;
    }
    public Date getDateTime() {
        return DateTime;
    }

    public void setBarcode(String Barcode) {
        this.Barcode = Barcode;
    }
    public String getBarcode() {
        return Barcode;
    }

    public void setGNDResult(String GNDResult) {
        this.GNDResult = GNDResult;
    }
    public String getGNDResult() {
        return GNDResult;
    }

    public void setACWResult(String ACWResult) {
        this.ACWResult = ACWResult;
    }
    public String getACWResult() {
        return ACWResult;
    }

    public void setVoltage(String Voltage) {
        this.Voltage = Voltage;
    }
    public String getVoltage() {
        return Voltage;
    }

    public void setPowerFactor(String PowerFactor) {
        this.PowerFactor = PowerFactor;
    }
    public String getPowerFactor() {
        return PowerFactor;
    }

    public void setPower1(String Power1) {
        this.Power1 = Power1;
    }
    public String getPower1() {
        return Power1;
    }

    public void setPower2(String Power2) {
        this.Power2 = Power2;
    }
    public String getPower2() {
        return Power2;
    }

    public void setPower3(String Power3) {
        this.Power3 = Power3;
    }
    public String getPower3() {
        return Power3;
    }

    public void setPower4(String Power4) {
        this.Power4 = Power4;
    }
    public String getPower4() {
        return Power4;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }
    public String getResult() {
        return Result;
    }
}
