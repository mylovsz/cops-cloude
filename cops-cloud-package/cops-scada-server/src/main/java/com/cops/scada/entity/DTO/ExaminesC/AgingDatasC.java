package com.cops.scada.entity.DTO.ExaminesC;

import java.util.Date;

public class AgingDatasC {
    private Date DateTime;
    private String Temperature;
    private String VoltageMax;
    private String Voltage;
    private String VoltageMin;
    private String PowerMax;
    private String Power;
    private String PowerMin;
    private String PFMax;
    private String PF;
    private String PFMin;
    private String CurrentMax;
    private String Current;
    private String CurrentMin;
    private String Result;
    public void setDateTime(Date DateTime) {
        this.DateTime = DateTime;
    }
    public Date getDateTime() {
        return DateTime;
    }

    public void setTemperature(String Temperature) {
        this.Temperature = Temperature;
    }
    public String getTemperature() {
        return Temperature;
    }

    public void setVoltageMax(String VoltageMax) {
        this.VoltageMax = VoltageMax;
    }
    public String getVoltageMax() {
        return VoltageMax;
    }

    public void setVoltage(String Voltage) {
        this.Voltage = Voltage;
    }
    public String getVoltage() {
        return Voltage;
    }

    public void setVoltageMin(String VoltageMin) {
        this.VoltageMin = VoltageMin;
    }
    public String getVoltageMin() {
        return VoltageMin;
    }

    public void setPowerMax(String PowerMax) {
        this.PowerMax = PowerMax;
    }
    public String getPowerMax() {
        return PowerMax;
    }

    public void setPower(String Power) {
        this.Power = Power;
    }
    public String getPower() {
        return Power;
    }

    public void setPowerMin(String PowerMin) {
        this.PowerMin = PowerMin;
    }
    public String getPowerMin() {
        return PowerMin;
    }

    public void setPFMax(String PFMax) {
        this.PFMax = PFMax;
    }
    public String getPFMax() {
        return PFMax;
    }

    public void setPF(String PF) {
        this.PF = PF;
    }
    public String getPF() {
        return PF;
    }

    public void setPFMin(String PFMin) {
        this.PFMin = PFMin;
    }
    public String getPFMin() {
        return PFMin;
    }

    public void setCurrentMax(String CurrentMax) {
        this.CurrentMax = CurrentMax;
    }
    public String getCurrentMax() {
        return CurrentMax;
    }

    public void setCurrent(String Current) {
        this.Current = Current;
    }
    public String getCurrent() {
        return Current;
    }

    public void setCurrentMin(String CurrentMin) {
        this.CurrentMin = CurrentMin;
    }
    public String getCurrentMin() {
        return CurrentMin;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }
    public String getResult() {
        return Result;
    }
}
