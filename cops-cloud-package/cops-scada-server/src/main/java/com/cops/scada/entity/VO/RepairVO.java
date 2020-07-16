package com.cops.scada.entity.VO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;
import com.cops.scada.entity.ExcelColumn;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>
 * 维修管理
 * </p>
 *
 * @author wanglm
 * @since 2019-04-17
 */
@TableName("scada_repair")
public class RepairVO extends DataEntity<RepairVO> {

    private static final long serialVersionUID = 1L;

	/**
	 * 不良代码（Label）
	 */
	@Setter
	@Getter
	@ExcelColumn(value = "不良代码", col = 6)
	private String faultCodeLabel;

	/**
	 * 状态（Label）
	 */
	@Setter
	@Getter
	@ExcelColumn(value = "状态", col = 18)
	private String stateLabel;

	/**
	 * 责任部门（Label）
	 */
	@Setter
	@Getter
	@ExcelColumn(value = "责任部门", col = 11)
	private String responsibleDepartmentLabel;

	/**
	 * 生产工单
	 */
	@Setter
	@Getter
	@ExcelColumn(value = "工单号", col = 2)
	private String planSN;

	/**
	 * 生产产品类型
	 */
	@Setter
	@Getter
	@ExcelColumn(value = "产品类型", col = 3)
	private String productSN;

	/**
	 * 生产产品
	 */
	@Setter
	@Getter
	@ExcelColumn(value = "产品编码", col = 4)
	private String produceSN;

	/**
	 * 不良站
	 */
	@Setter
	@Getter
	@ExcelColumn(value = "不良站", col = 5)
	private String factorySiteName;

	/**
	 * 送修人
	 */
	@Setter
	@Getter
	@ExcelColumn(value = "送修人", col = 8)
	private String faultLoginName;

	/**
	 * 送修人
	 */
	@Setter
	@Getter
	private String faultNickName;

	/**
	 * 责任人
	 */
	@Setter
	@Getter
	private String responsibleLoginName;

	/**
	 * 责任人
	 */
	@Setter
	@Getter
	private String responsibleNickName;

	/**
	 * 维修人
	 */
	@Setter
	@Getter
	@ExcelColumn(value = "维修人", col = 13)
	private String repairLoginName;

	/**
	 * 维修人
	 */
	@Setter
	@Getter
	private String repairNickName;

	/**
	 * 取回人
	 */
	@Setter
	@Getter
	@ExcelColumn(value = "取回人", col = 16)
	private String takeLoginName;

	/**
	 * 取回人
	 */
	@Setter
	@Getter
	private String takeNickName;

    /**
     * 维修单编号
     */
	@ExcelColumn(value = "维修单号", col=1)
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
	@ExcelColumn(value = "不良现象", col = 7)
	private String faultAppearance;
    /**
     * 送修时间
     */
	@TableField("fault_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ExcelColumn(value = "送修时间", col = 9)
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
	@ExcelColumn(value = "不良原因", col = 10)
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
	@ExcelColumn(value = "维修方案", col = 12)
	private String repairWay;
    /**
     * 维修时间
     */
	@TableField("repair_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ExcelColumn(value = "维修时间", col = 14)
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
	@ExcelColumn(value = "取回备注", col = 15)
	private String takeRemark;
    /**
     * 取回时间
     */
	@TableField("take_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ExcelColumn(value = "取回时间", col = 17)
	private Date takeDate;
    /**
     * 状态
     */
	@ExcelColumn(value = "状态")
	private Integer state;

	/**
	 * 责任部门-部门
	 */
	@TableField("responsible_department")
	@Setter
	@Getter
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
