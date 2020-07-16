package com.cops.scada.entity.DTO.ExaminesD;

import java.util.Date;

public class SiteDatasD {
    private String UserName;
    private String Product;
    private String Barcode;
    private String Step;
    private String Type;
    private String Result;
    private String Meter1;
    private String Meter2;
    private String Meter3;
    private String Meter4;
    private Date DateTime;
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public String getUserName() {
        return UserName;
    }

    public void setProduct(String Product) {
        this.Product = Product;
    }
    public String getProduct() {
        return Product;
    }

    public void setBarcode(String Barcode) {
        this.Barcode = Barcode;
    }
    public String getBarcode() {
        return Barcode;
    }

    public void setStep(String Step) {
        this.Step = Step;
    }
    public String getStep() {
        return Step;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
    public String getType() {
        return Type;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }
    public String getResult() {
        return Result;
    }

    public void setMeter1(String Meter1) {
        this.Meter1 = Meter1;
    }
    public String getMeter1() {
        return Meter1;
    }

    public void setMeter2(String Meter2) {
        this.Meter2 = Meter2;
    }
    public String getMeter2() {
        return Meter2;
    }

    public void setMeter3(String Meter3) {
        this.Meter3 = Meter3;
    }
    public String getMeter3() {
        return Meter3;
    }

    public void setMeter4(String Meter4) {
        this.Meter4 = Meter4;
    }
    public String getMeter4() {
        return Meter4;
    }

    public void setDateTime(Date DateTime) {
        this.DateTime = DateTime;
    }
    public Date getDateTime() {
        return DateTime;
    }
}
