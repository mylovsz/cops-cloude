package com.cops.scada.entity.VO;

import lombok.Data;

/**
 * 产品属性和值
 *
 * @Author: wangermao
 * @Date: 2020-05-09 11:03
 * @Version: V1.0
 */
@Data
public class ProductInfoAttrAndValueVO {

    /**
     * 系列名称
     */
    private String seriesName;

    /**
     * 产品名称
     */
    private String productInfoName;

    /**
     * 属性ID
     */
    private String seriesAttrId;

    /**
     * 属性名称
     */
    private String seriesAttrName;

    /**
     * 属性值类型
     */
    private Integer seriesAttrValueType;

    /**
     * 属性单位
     */
    private String seriesAttrCompany;

    /**
     * 属性排序
     */
    private String seriesAttrSort;

    /**
     * 属性值
     */
    private String productInfoAttrValue;

    /**
     * 属性值en
     */
    private String productInfoAttrValueEn;

    public String toShow() {
        switch (seriesAttrValueType){
            case 0:
            case 1:
            case 4:
                return productInfoAttrValue+" "+seriesAttrCompany;
            case 3:
                return productInfoAttrValue+" ~ "+productInfoAttrValueEn +" "+seriesAttrCompany;
            default:
                return "";
        }
    }

    public String toShowOne() {
        switch (seriesAttrValueType){
            case 0:
            case 1:
            case 4:
                return seriesAttrName + "：" + productInfoAttrValue+" "+seriesAttrCompany;
            case 3:
                return seriesAttrName + "：" + productInfoAttrValue+" ~ "+productInfoAttrValueEn +" "+seriesAttrCompany;
            default:
                return "";
        }
    }
}
