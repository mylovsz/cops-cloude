package com.cops.entity.nc65;

import lombok.Data;

import java.util.Date;

@Data
public class BomOnhand {

    /**
     * 行号
     */
    Integer nu;
    /**
     * 实际需求数量
     */
    Double requireNum;
    /**
     * 短缺
     */
    Double shortage;
    /**
     * BOM的主键ID
     */
    String bomId;
    /**
     * 实际需要的数量
     */
    Double num;
    /**
     * 底数
     */
    Double baseNum;
    /**
     * 物料主ID
     */
    String bMaterialId;
    /**
     * 物料编码
     */
    String bMaterialCode;
    /**
     * 物料名称
     */
    String bMaterialName;
    /**
     * 现存量
     */
    Double handNum;
    /**
     * 仓库名称
     */
    String storName;
    /**
     * 物料描述
     */
    String bMaterialSpec;
    /**
     * BOM主版本主键
     */
    String materialId;
    /**
     * BOM主版本编码
     */
    String materialCode;
    /**
     * BOM主版本名称
     */
    String materialName;
    /**
     * BOM主版本号
     */
    String version;

    /**
     * 设计报价相关的内容
     */
    SupplierPrice supplierPrice = new SupplierPrice();

    /**
     * 币种
     */
    public String getCurrtypeCurrtypesign(){
        return this.supplierPrice == null ? "" : this.supplierPrice.getCurrtypeCurrtypesign();
    }

    public void setCurrtypeCurrtypesign(String value) {
        if(this.supplierPrice != null) {
            this.supplierPrice.setCurrtypeCurrtypesign(value);
        }
    }

    /**
     * 税率
     */
    public Double getTaxRate(){
        return this.supplierPrice == null ? 0.0 : this.supplierPrice.getTaxRate();
    }
    public void setTaxRate(Double value){
        if(this.supplierPrice != null){
            this.supplierPrice.setTaxRate(value);
        }
    }

    /**
     * 无税单价
     */
    public Double getOrigPrice(){
        return this.supplierPrice == null ? 0.0 : this.supplierPrice.getOrigPrice();
    }
    public void setOrigPrice(Double value){
        if(this.supplierPrice != null) {
            this.supplierPrice.setOrigPrice(value);
        }
    }

    /**
     * 含税单价
     */
    public Double getOrigTaxPrice(){
        return this.supplierPrice == null ? 0.0 : this.supplierPrice.getOrigTaxPrice();
    }
    public void setOrigTaxPrice(Double value){
        if(this.supplierPrice != null){
            this.supplierPrice.setOrigTaxPrice(value);
        }
    }

    /**
     * 生效日期
     */
    public Date getValidDate(){
        return this.supplierPrice == null ? null : this.supplierPrice.getValidDate();
    }
    public void setValidDate(Date value){
        if(this.supplierPrice != null) {
            this.supplierPrice.setValidDate(value);
        }
    }

    /**
     * 失效日期
     */
    public Date getInvalidDate(){
        return this.supplierPrice == null ? null : this.supplierPrice.getInvalidDate();
    }
    public void setInvalidDate(Date value){
        if(this.supplierPrice != null) {
            this.supplierPrice.setInvalidDate(value);
        }
    }
}
