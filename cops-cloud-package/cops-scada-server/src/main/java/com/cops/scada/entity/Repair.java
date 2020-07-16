package com.cops.scada.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 维修管理
 * </p>
 *
 * @author wanglm
 * @since 2019-04-17
 */
@TableName("scada_repair")
public class Repair extends DataEntity<Repair> {

    private static final long serialVersionUID = 1L;

    /**
     * 维修单编号
     */
	private String sn;
    /**
     * 生产产品ID
     */
	@TableField("produce_id")
	private Long produceId;
    /**
     * 不良站
     */
	@TableField("factory_site_id")
	private Long factorySiteId;
    /**
     * 不良代码
     */
	@TableField("fault_code")
	private String faultCode;
    /**
     * 不良现象
     */
	@TableField("fault_appearance")
	private String faultAppearance;
    /**
     * 送修时间
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
	@TableField("responsible_department_user_id")
	private Long responsibleDepartmentUserId;
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
     * 取回人
     */
	@TableField("take_user_id")
	private Long takeUserId;
    /**
     * 取回备注
     */
	@TableField("take_remark")
	private String takeRemark;
    /**
     * 取回时间
     */
	@TableField("take_date")
	private Date takeDate;
    /**
     * 状态
     */
	private Integer state;
	/**
	 * 责任部门-部门
	 */
	@TableField("responsible_department")
	@Getter
	@Setter
	private String responsibleDepartment;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
	public Long getProduceId() {
		return produceId;
	}

	public void setProduceId(Long produceId) {
		this.produceId = produceId;
	}
	public Long getFactorySiteId() {
		return factorySiteId;
	}

	public void setFactorySiteId(Long factorySiteId) {
		this.factorySiteId = factorySiteId;
	}
	public String getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
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
	public Long getResponsibleDepartmentUserId() {
		return responsibleDepartmentUserId;
	}

	public void setResponsibleDepartmentUserId(Long responsibleDepartmentUserId) {
		this.responsibleDepartmentUserId = responsibleDepartmentUserId;
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
	public Long getTakeUserId() {
		return takeUserId;
	}

	public void setTakeUserId(Long takeUserId) {
		this.takeUserId = takeUserId;
	}
	public String getTakeRemark() {
		return takeRemark;
	}

	public void setTakeRemark(String takeRemark) {
		this.takeRemark = takeRemark;
	}
	public Date getTakeDate() {
		return takeDate;
	}

	public void setTakeDate(Date takeDate) {
		this.takeDate = takeDate;
	}
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "Repair{" +
			", sn=" + sn +
			", produceId=" + produceId +
			", factorySiteId=" + factorySiteId +
			", faultCode=" + faultCode +
			", faultAppearance=" + faultAppearance +
			", faultDate=" + faultDate +
			", faultUserId=" + faultUserId +
			", faultCause=" + faultCause +
			", responsibleDepartmentUserId=" + responsibleDepartmentUserId +
			", repairWay=" + repairWay +
			", repairDate=" + repairDate +
			", repairUserId=" + repairUserId +
			", takeUserId=" + takeUserId +
			", takeRemark=" + takeRemark +
			", takeDate=" + takeDate +
			", state=" + state +
			"}";
	}
}
