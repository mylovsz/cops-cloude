package com.cops.scada.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 展示产品
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
@TableName("scada_product_info")
public class ProductInfo extends DataEntity<ProductInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
	private String name;

	/**
	 * 简写
	 */
	@Getter
	@Setter
	private String shortName;

	/**
	 * 产品状态，0-完成，1-新品，2-规划中
	 */
	@Getter
	@Setter
	private String state;

    /**
     * 名称（英文）
     */
	@TableField("name_en")
	private String nameEn;
    /**
     * 系列id
     */
	@TableField("series_id")
	private Long seriesId;
    /**
     * 详情富文本
     */
	private String content;
    /**
     * 详情（英文）
     */
	@TableField("content_en")
	private String contentEn;
    /**
     * 是否启用
     */
	@TableField("is_used")
	private Integer isUsed;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public Long getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(Long seriesId) {
		this.seriesId = seriesId;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getContentEn() {
		return contentEn;
	}

	public void setContentEn(String contentEn) {
		this.contentEn = contentEn;
	}
	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}


	@Override
	public String toString() {
		return "ProductInfo{" +
			", name=" + name +
			", nameEn=" + nameEn +
			", seriesId=" + seriesId +
			", content=" + content +
			", contentEn=" + contentEn +
			", isUsed=" + isUsed +
			"}";
	}
}
