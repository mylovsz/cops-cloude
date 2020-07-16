package com.cops.scada.entity.DTO.ExaminesC;

import java.util.List;

public class SiteDatasC {
    private String ProductModel;
    private String Number;
    private String Barcode;
    private List<AgingDatasC> AgingDatas;
    public void setProductModel(String ProductModel) {
        this.ProductModel = ProductModel;
    }
    public String getProductModel() {
        return ProductModel;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }
    public String getNumber() {
        return Number;
    }

    public void setBarcode(String Barcode) {
        this.Barcode = Barcode;
    }
    public String getBarcode() {
        return Barcode;
    }

    public void setAgingDatas(List<AgingDatasC> AgingDatas) {
        this.AgingDatas = AgingDatas;
    }
    public List<AgingDatasC> getAgingDatas() {
        return AgingDatas;
    }
}
