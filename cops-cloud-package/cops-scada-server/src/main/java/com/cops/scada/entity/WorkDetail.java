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
 * 工时详情
 * </p>
 *
 * @author wanglm
 * @since 2020-04-27
 */
@TableName("scada_work_detail")
public class WorkDetail extends DataEntity<WorkDetail> {

    private static final long serialVersionUID = 1L;

	/**
	 * 加工时长
	 */
	@Setter
	@Getter
    @TableField("work_process_time")
    private Long workProcessTime;

    /**
     * 工时管理编号
     */
	@TableField("man_hour_id")
	private Long manHourId;
    /**
     * 日计划编号
     */
	@TableField("plan_day_id")
	private Long planDayId;
    /**
     * 生产计划编号
     */
	@TableField("plan_id")
	private Long planId;
    /**
     * 工艺编号
     */
	@TableField("job_id")
	private Long jobId;
    /**
     * 加工人
     */
	@TableField("start_work_user_id")
	private Long startWorkUserId;
    /**
     * 补偿时间
     */
	@TableField("reimbursed_time")
	private Long reimbursedTime;
    /**
     * 加工人数
     */
	@TableField("start_work_user_num")
	private Integer startWorkUserNum;
    /**
     * 半成品投料
     */
	@TableField("start_in_num")
	private Long startInNum;
    /**
     * 计划完成数量
     */
	@TableField("start_finish_num")
	private Long startFinishNum;
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
	private Long endQuantityCount;
    /**
     * 备注
     */
	@TableField("end_quantity_remarks")
	private String endQuantityRemarks;
    /**
     * 报废数量
     */
	@TableField("end_scrap_count")
	private Long endScrapCount;
    /**
     * 报废理由
     */
	@TableField("end_scrap_reason")
	private String endScrapReason;
    /**
     * 报废备注
     */
	@TableField("end_scrap_remarks")
	private String endScrapRemarks;
    /**
     * 返修数量
     */
	@TableField("end_repair_count")
	private Long endRepairCount;
    /**
     * 返修原因
     */
	@TableField("end_repair_reason")
	private String endRepairReason;
    /**
     * 返修备注
     */
	@TableField("end_repair_remark")
	private String endRepairRemark;
    /**
     * 加工结束时间
     */
	@TableField("end_date")
	private Date endDate;

    /**
     * 状态
     */
	private Integer state;

	public Long getManHourId() {
		return manHourId;
	}

	public void setManHourId(Long manHourId) {
		this.manHourId = manHourId;
	}
	public Long getPlanDayId() {
		return planDayId;
	}

	public void setPlanDayId(Long planDayId) {
		this.planDayId = planDayId;
	}
	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public Long getStartWorkUserId() {
		return startWorkUserId;
	}

	public void setStartWorkUserId(Long startWorkUserId) {
		this.startWorkUserId = startWorkUserId;
	}
	public Long getReimbursedTime() {
		return reimbursedTime;
	}

	public void setReimbursedTime(Long reimbursedTime) {
		this.reimbursedTime = reimbursedTime;
	}
	public Integer getStartWorkUserNum() {
		return startWorkUserNum;
	}

	public void setStartWorkUserNum(Integer startWorkUserNum) {
		this.startWorkUserNum = startWorkUserNum;
	}
	public Long getStartInNum() {
		return startInNum;
	}

	public void setStartInNum(Long startInNum) {
		this.startInNum = startInNum;
	}
	public Long getStartFinishNum() {
		return startFinishNum;
	}

	public void setStartFinishNum(Long startFinishNum) {
		this.startFinishNum = startFinishNum;
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
	public Long getEndQuantityCount() {
		return endQuantityCount;
	}

	public void setEndQuantityCount(Long endQuantityCount) {
		this.endQuantityCount = endQuantityCount;
	}
	public String getEndQuantityRemarks() {
		return endQuantityRemarks;
	}

	public void setEndQuantityRemarks(String endQuantityRemarks) {
		this.endQuantityRemarks = endQuantityRemarks;
	}
	public Long getEndScrapCount() {
		return endScrapCount;
	}

	public void setEndScrapCount(Long endScrapCount) {
		this.endScrapCount = endScrapCount;
	}
	public String getEndScrapReason() {
		return endScrapReason;
	}

	public void setEndScrapReason(String endScrapReason) {
		this.endScrapReason = endScrapReason;
	}
	public String getEndScrapRemarks() {
		return endScrapRemarks;
	}

	public void setEndScrapRemarks(String endScrapRemarks) {
		this.endScrapRemarks = endScrapRemarks;
	}
	public Long getEndRepairCount() {
		return endRepairCount;
	}

	public void setEndRepairCount(Long endRepairCount) {
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
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "WorkDetail{" +
			", manHourId=" + manHourId +
			", planDayId=" + planDayId +
			", planId=" + planId +
			", jobId=" + jobId +
			", startWorkUserId=" + startWorkUserId +
			", reimbursedTime=" + reimbursedTime +
			", startWorkUserNum=" + startWorkUserNum +
			", startInNum=" + startInNum +
			", startFinishNum=" + startFinishNum +
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
			", state=" + state +
			"}";
	}
}
