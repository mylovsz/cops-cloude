package com.cops.scada.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;

/**
 * <p>
 * 设备维修
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@TableName("scada_device_repair")
public class DeviceRepair extends DataEntity<DeviceRepair> {

    private static final long serialVersionUID = 1L;

    /**
     * 设备关联
     */
	@TableField("device_id")
	private Long deviceId;
    /**
     * 不良现象
     */
	@TableField("fault_appearance")
	private String faultAppearance;
    /**
     * 不良时间
     */
	@TableField("fault_date")
	private Date faultDate;
    /**
     * 送修人
     */
	@TableField("fault_user_id")
	private Long faultUserId;
    /**
     * 不良原因
     */
	@TableField("fault_cause")
	private String faultCause;
    /**
     * 责任部门
     */
	@TableField("responsible_department")
	private String responsibleDepartment;
    /**
     * 维修方案
     */
	@TableField("repair_way")
	private String repairWay;
    /**
     * 维修时间
     */
	@TableField("repair_date")
	private Date repairDate;
    /**
     * 维修人
     */
	@TableField("repair_user_id")
	private Long repairUserId;
    /**
     * 确认人
     */
	@TableField("ensure_user_id")
	private Long ensureUserId;
    /**
     * 确认时间
     */
	@TableField("ensure_date")
	private Date ensureDate;

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public String getFaultAppearance() {
		return faultAppearance;
	}

	public void setFaultAppearance(String faultAppearance) {
		this.faultAppearance = faultAppearance;
	}
	public Date getFaultDate() {
		return faultDate;
	}

	public void setFaultDate(Date faultDate) {
		this.faultDate = faultDate;
	}
	public Long getFaultUserId() {
		return faultUserId;
	}

	public void setFaultUserId(Long faultUserId) {
		this.faultUserId = faultUserId;
	}
	public String getFaultCause() {
		return faultCause;
	}

	public void setFaultCause(String faultCause) {
		this.faultCause = faultCause;
	}
	public String getResponsibleDepartment() {
		return responsibleDepartment;
	}

	public void setResponsibleDepartment(String responsibleDepartment) {
		this.responsibleDepartment = responsibleDepartment;
	}
	public String getRepairWay() {
		return repairWay;
	}

	public void setRepairWay(String repairWay) {
		this.repairWay = repairWay;
	}
	public Date getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}
	public Long getRepairUserId() {
		return repairUserId;
	}

	public void setRepairUserId(Long repairUserId) {
		this.repairUserId = repairUserId;
	}
	public Long getEnsureUserId() {
		return ensureUserId;
	}

	public void setEnsureUserId(Long ensureUserId) {
		this.ensureUserId = ensureUserId;
	}
	public Date getEnsureDate() {
		return ensureDate;
	}

	public void setEnsureDate(Date ensureDate) {
		this.ensureDate = ensureDate;
	}


	@Override
	public String toString() {
		return "DeviceRepair{" +
			", deviceId=" + deviceId +
			", faultAppearance=" + faultAppearance +
			", faultDate=" + faultDate +
			", faultUserId=" + faultUserId +
			", faultCause=" + faultCause +
			", responsibleDepartment=" + responsibleDepartment +
			", repairWay=" + repairWay +
			", repairDate=" + repairDate +
			", repairUserId=" + repairUserId +
			", ensureUserId=" + ensureUserId +
			", ensureDate=" + ensureDate +
			"}";
	}
}
