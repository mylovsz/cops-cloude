package com.cops.scada.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;

/**
 * <p>
 * 设备供应商
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@TableName("scada_supplier")
public class Supplier extends DataEntity<Supplier> {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商编号
     */
	private String sn;
    /**
     * 供应商名称
     */
	private String name;
    /**
     * 供应商地址
     */
	private String addr;
    /**
     * 传真
     */
	private String fax;
    /**
     * 邮箱
     */
	private String mail;
    /**
     * 联系人
     */
	private String contacts;
    /**
     * 联系电话
     */
	private String telephone;
    /**
     * 状态
     */
	private Integer state;
    /**
     * 生产商名称
     */
	@TableField("manufacture_name")
	private String manufactureName;

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
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	public String getManufactureName() {
		return manufactureName;
	}

	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}


	@Override
	public String toString() {
		return "Supplier{" +
			", sn=" + sn +
			", name=" + name +
			", addr=" + addr +
			", fax=" + fax +
			", mail=" + mail +
			", contacts=" + contacts +
			", telephone=" + telephone +
			", state=" + state +
			", manufactureName=" + manufactureName +
			"}";
	}
}
