package com.cops.scada.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;

/**
 * <p>
 * 设备校检
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@TableName("scada_device_check")
public class DeviceCheck extends DataEntity<DeviceCheck> {

    private static final long serialVersionUID = 1L;

    /**
     * 例检编号
     */
	private String sn;
    /**
     * 设备关联字段
     */
	@TableField("device_id")
	private Long deviceId;
    /**
     * 类型
     */
	private Integer type;
    /**
     * 例检人
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 例检时间
     */
	@TableField("check_date")
	private Date checkDate;
    /**
     * 例检结果
     */
	private String result;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}


	@Override
	public String toString() {
		return "DeviceCheck{" +
			", sn=" + sn +
			", deviceId=" + deviceId +
			", type=" + type +
			", userId=" + userId +
			", checkDate=" + checkDate +
			", result=" + result +
			"}";
	}
}
