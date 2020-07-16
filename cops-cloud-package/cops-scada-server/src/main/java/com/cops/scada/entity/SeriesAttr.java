package com.cops.scada.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.TreeEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 属性
 * </p>
 *
 * @author wanglm
 * @since 2020-04-21
 */
@TableName("scada_series_attr")
public class SeriesAttr extends TreeEntity<SeriesAttr> {

	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 */
	private String name;
	/**
	 * 名称（英文）
	 */
	@TableField("name_en")
	private String nameEn;
	/**
	 * 值类型
	 */
	@TableField("value_type")
	private String valueType;
	/**
	 * 是否加入到筛选
	 */
	@TableField("is_search")
	private Integer isSearch;
	/**
	 * 单位
	 */
	private String company;

	/**
	 * 类型
	 */
	@Getter
	@Setter
	private Integer type;

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
	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public Integer getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(Integer isSearch) {
		this.isSearch = isSearch;
	}
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}


	@Override
	public String toString() {
		return "SeriesAttr{" +
				", name=" + name +
				", nameEn=" + nameEn +
				", valueType=" + valueType +
				", isSearch=" + isSearch +
				", company=" + company +
				"}";
	}
}
