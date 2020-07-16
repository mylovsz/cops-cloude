package com.cops.entity.nc65;

import lombok.Data;

import java.util.Date;

@Data
public class SupplierPrice {

    public SupplierPrice() {
        this.materialId = "";
        this.materialCode = "";
        this.materialName = "";
        this.currtypeCurrtypesign = "";
        this.taxRate = 0.0;
        this.origPrice = 0.0;
        this.origTaxPrice = 0.0;
        this.validDate = null;
        this.invalidDate = null;
    }

    /**
     * 物料主ID
     */
    private String materialId;
    /**
     * 物料编码
     */
    private String materialCode;
    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 币种
     */
    private String currtypeCurrtypesign;

    /**
     * 税率
     */
    private Double taxRate;

    /**
     * 无税单价
     */
    private Double origPrice;

    /**
     * 含税单价
     */
    private Double origTaxPrice;

    /**
     * 生效日期
     */
    private Date validDate;

    /**
     * 失效日期
     */
    private Date invalidDate;
}
