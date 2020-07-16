package com.cops.scada.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;

/**
 * <p>
 * 产品表
 * </p>
 *
 * @author wanglm
 * @since 2019-03-11
 */
@TableName("scada_product")
public class Product extends DataEntity<Product> {

    private static final long serialVersionUID = 1L;

    /**
     * 产品编号
     */
	private String sn;
    /**
     * 产品名称
     */
	private String name;
    /**
     * 产品类型
     */
	private Integer type;
    /**
     * 产品介绍
     */
	private String introduction;
    /**
     * BOM编号
     */
	private String bom;
    /**
     * 附件
     */
	private String attachment;
    /**
     * 状态
     */
	private Integer state;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getBom() {
		return bom;
	}

	public void setBom(String bom) {
		this.bom = bom;
	}
	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "Product{" +
			", sn=" + sn +
			", name=" + name +
			", type=" + type +
			", introduction=" + introduction +
			", bom=" + bom +
			", attachment=" + attachment +
			", state=" + state +
			"}";
	}
}
