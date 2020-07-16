package com.cops.scada.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;

/**
 * <p>
 * 工卡信息
 * </p>
 *
 * @author wanglm
 * @since 2020-04-08
 */
@TableName("scada_user_card")
public class UserCard extends DataEntity<UserCard> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 类型
     */
	private String type;
    /**
     * 进厂日期
     */
	@TableField("join_date")
	private Date joinDate;
    /**
     * 工号
     */
	private String sn;
    /**
     * 姓名
     */
	private String name;
    /**
     * 部门
     */
	private String department;
    /**
     * 职务
     */
	private String duty;
    /**
     * 卡号
     */
	@TableField("card_sn")
	private String cardSn;
    /**
     * 办卡日期
     */
	@TableField("create_card_date")
	private Date createCardDate;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
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
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getCardSn() {
		return cardSn;
	}

	public void setCardSn(String cardSn) {
		this.cardSn = cardSn;
	}
	public Date getCreateCardDate() {
		return createCardDate;
	}

	public void setCreateCardDate(Date createCardDate) {
		this.createCardDate = createCardDate;
	}


	@Override
	public String toString() {
		return "UserCard{" +
			", userId=" + userId +
			", type=" + type +
			", joinDate=" + joinDate +
			", sn=" + sn +
			", name=" + name +
			", department=" + department +
			", duty=" + duty +
			", cardSn=" + cardSn +
			", createCardDate=" + createCardDate +
			"}";
	}
}
