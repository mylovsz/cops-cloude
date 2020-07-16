package com.cops.scada.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.TreeEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系列
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
@TableName("scada_series")
public class Series extends TreeEntity<Series> {

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
     * 父级id
     */
	@TableField("father_id")
	private Long fatherId;
    /**
     * 类型，groupby 获取进行分类
     */
	@TableField("type_string")
	private String typeString;

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
	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}
	public String getTypeString() {
		return typeString;
	}

	public void setTypeString(String typeString) {
		this.typeString = typeString;
	}


	@Override
	public String toString() {
		return "Series{" +
			", name=" + name +
			", nameEn=" + nameEn +
			", fatherId=" + fatherId +
			", typeString=" + typeString +
			"}";
	}
}
