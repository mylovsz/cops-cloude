package com.cops.scada.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;

/**
 * <p>
 * 展示产品属性值
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
@TableName("scada_product_info_attr_value")
public class ProductInfoAttrValue extends DataEntity<ProductInfoAttrValue> {

    private static final long serialVersionUID = 1L;

    /**
     * 展示产品关联
     */
	@TableField("product_info_id")
	private Long productInfoId;
    /**
     * 属性关联id
     */
	@TableField("series_attr_id")
	private Long seriesAttrId;
    /**
     * 属性值
     */
	private String value;
    /**
     * 属性值（英文）
     */
	@TableField("value_en")
	private String valueEn;

	public Long getProductInfoId() {
		return productInfoId;
	}

	public void setProductInfoId(Long productInfoId) {
		this.productInfoId = productInfoId;
	}
	public Long getSeriesAttrId() {
		return seriesAttrId;
	}

	public void setSeriesAttrId(Long seriesAttrId) {
		this.seriesAttrId = seriesAttrId;
	}
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public String getValueEn() {
		return valueEn;
	}

	public void setValueEn(String valueEn) {
		this.valueEn = valueEn;
	}


	@Override
	public String toString() {
		return "ProductInfoAttrValue{" +
			", productInfoId=" + productInfoId +
			", seriesAttrId=" + seriesAttrId +
			", value=" + value +
			", valueEn=" + valueEn +
			"}";
	}
}
