package com.cops.scada.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;

/**
 * <p>
 * 工时管理
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@TableName("scada_man_hour")
public class ManHour extends DataEntity<ManHour> {

    private static final long serialVersionUID = 1L;

    /**
     * 设备编号
     */
	@TableField("device_id")
	private Long deviceId;
    /**
     * 工艺编号
     */
	@TableField("job_id")
	private Long jobId;
    /**
     * 加工人
     */
	@TableField("start_job_user_id")
	private Long startJobUserId;
    /**
     * 半成品投料
     */
	@TableField("start_in_count")
	private BigDecimal startInCount;
    /**
     * 计划完成数量
     */
	@TableField("start_finish_count")
	private BigDecimal startFinishCount;
    /**
     * 备注
     */
	@TableField("start_remarks")
	private String startRemarks;
    /**
     * 加工开始时间
     */
	@TableField("start_date")
	private Date startDate;
    /**
     * 提交人
     */
	@TableField("end_job_user_id")
	private Long endJobUserId;
    /**
     * 合格产品数量
     */
	@TableField("end_quantity_count")
	private BigDecimal endQuantityCount;
    /**
     * 合格备注
     */
	@TableField("end_quantity_remarks")
	private BigDecimal endQuantityRemarks;
    /**
     * 报废数量
     */
	@TableField("end_scrap_count")
	private BigDecimal endScrapCount;
    /**
     * 报废理由
     */
	@TableField("end_scrap_reason")
	private BigDecimal endScrapReason;
    /**
     * 报废备注
     */
	@TableField("end_scrap_remarks")
	private String endScrapRemarks;
    /**
     * 返工数量
     */
	@TableField("end_repair_count")
	private BigDecimal endRepairCount;
    /**
     * 返工原因
     */
	@TableField("end_repair_reason")
	private String endRepairReason;
    /**
     * 返工备注
     */
	@TableField("end_repair_remark")
	private String endRepairRemark;
    /**
     * 加工结束时间
     */
	@TableField("end_date")
	private Date endDate;

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public Long getStartJobUserId() {
		return startJobUserId;
	}

	public void setStartJobUserId(Long startJobUserId) {
		this.startJobUserId = startJobUserId;
	}
	public BigDecimal getStartInCount() {
		return startInCount;
	}

	public void setStartInCount(BigDecimal startInCount) {
		this.startInCount = startInCount;
	}
	public BigDecimal getStartFinishCount() {
		return startFinishCount;
	}

	public void setStartFinishCount(BigDecimal startFinishCount) {
		this.startFinishCount = startFinishCount;
	}
	public String getStartRemarks() {
		return startRemarks;
	}

	public void setStartRemarks(String startRemarks) {
		this.startRemarks = startRemarks;
	}
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Long getEndJobUserId() {
		return endJobUserId;
	}

	public void setEndJobUserId(Long endJobUserId) {
		this.endJobUserId = endJobUserId;
	}
	public BigDecimal getEndQuantityCount() {
		return endQuantityCount;
	}

	public void setEndQuantityCount(BigDecimal endQuantityCount) {
		this.endQuantityCount = endQuantityCount;
	}
	public BigDecimal getEndQuantityRemarks() {
		return endQuantityRemarks;
	}

	public void setEndQuantityRemarks(BigDecimal endQuantityRemarks) {
		this.endQuantityRemarks = endQuantityRemarks;
	}
	public BigDecimal getEndScrapCount() {
		return endScrapCount;
	}

	public void setEndScrapCount(BigDecimal endScrapCount) {
		this.endScrapCount = endScrapCount;
	}
	public BigDecimal getEndScrapReason() {
		return endScrapReason;
	}

	public void setEndScrapReason(BigDecimal endScrapReason) {
		this.endScrapReason = endScrapReason;
	}
	public String getEndScrapRemarks() {
		return endScrapRemarks;
	}

	public void setEndScrapRemarks(String endScrapRemarks) {
		this.endScrapRemarks = endScrapRemarks;
	}
	public BigDecimal getEndRepairCount() {
		return endRepairCount;
	}

	public void setEndRepairCount(BigDecimal endRepairCount) {
		this.endRepairCount = endRepairCount;
	}
	public String getEndRepairReason() {
		return endRepairReason;
	}

	public void setEndRepairReason(String endRepairReason) {
		this.endRepairReason = endRepairReason;
	}
	public String getEndRepairRemark() {
		return endRepairRemark;
	}

	public void setEndRepairRemark(String endRepairRemark) {
		this.endRepairRemark = endRepairRemark;
	}
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	@Override
	public String toString() {
		return "ManHour{" +
			", deviceId=" + deviceId +
			", jobId=" + jobId +
			", startJobUserId=" + startJobUserId +
			", startInCount=" + startInCount +
			", startFinishCount=" + startFinishCount +
			", startRemarks=" + startRemarks +
			", startDate=" + startDate +
			", endJobUserId=" + endJobUserId +
			", endQuantityCount=" + endQuantityCount +
			", endQuantityRemarks=" + endQuantityRemarks +
			", endScrapCount=" + endScrapCount +
			", endScrapReason=" + endScrapReason +
			", endScrapRemarks=" + endScrapRemarks +
			", endRepairCount=" + endRepairCount +
			", endRepairReason=" + endRepairReason +
			", endRepairRemark=" + endRepairRemark +
			", endDate=" + endDate +
			"}";
	}
}
