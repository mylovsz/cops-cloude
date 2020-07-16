package com.cops.scada.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;

/**
 * <p>
 * 设备管理
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@TableName("scada_device")
public class Device extends DataEntity<Device> {

    private static final long serialVersionUID = 1L;

    /**
     * 设备编码
     */
	private String sn;
    /**
     * 设备名称
     */
	private String name;
    /**
     * 设备类型
     */
	private Integer type;
    /**
     * 设备规格
     */
	private String spec;
    /**
     * 设备供应商
     */
	@TableField("supplier_id")
	private Long supplierId;
    /**
     * 购买日期
     */
	@TableField("purchase_date")
	private Date purchaseDate;
    /**
     * 购买价格
     */
	private BigDecimal price;
    /**
     * 出厂日期
     */
	@TableField("manufacture_date")
	private Date manufactureDate;
    /**
     * 使用年限
     */
	private BigDecimal life;
    /**
     * 管理人
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 设备状态
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
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	public BigDecimal getLife() {
		return life;
	}

	public void setLife(BigDecimal life) {
		this.life = life;
	}
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "Device{" +
			", sn=" + sn +
			", name=" + name +
			", type=" + type +
			", spec=" + spec +
			", supplierId=" + supplierId +
			", purchaseDate=" + purchaseDate +
			", price=" + price +
			", manufactureDate=" + manufactureDate +
			", life=" + life +
			", userId=" + userId +
			", state=" + state +
			"}";
	}
}
