package com.cops.scada.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.TreeEntity;

/**
 * <p>
 * 车间站点信息
 * </p>
 *
 * @author wanglm
 * @since 2019-04-15
 */
@TableName("scada_factory_site")
public class FactorySite extends TreeEntity<FactorySite> {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
	private String sn;
    /**
     * 名称
     */
	private String name;
    /**
     * 类型
     */
	private Integer type;
    /**
     * 描述
     */
	private String description;
    /**
     * 链接地址
     */
	private String href;
    /**
     * 图标
     */
	private String logo;

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
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}


	@Override
	public String toString() {
		return "FactorySite{" +
			", sn=" + sn +
			", name=" + name +
			", type=" + type +
			", description=" + description +
			", href=" + href +
			", logo=" + logo +
			"}";
	}
}
