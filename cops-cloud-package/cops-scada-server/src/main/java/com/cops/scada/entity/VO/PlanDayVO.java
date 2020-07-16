package com.cops.scada.entity.VO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;
import com.cops.scada.entity.PlanDay;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 日计划统计
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@TableName("scada_plan_day")
public class PlanDayVO extends DataEntity<PlanDayVO> {

    private static final long serialVersionUID = 1L;

	@Setter
	@Getter
	private String productSn;

	@Setter
	@Getter
	private String productName;

    @Setter
	@Getter
	private String jobSn;

	@Setter
	@Getter
	private String jobName;

	@Setter
	@Getter
	private String jobIdentityType;

	@Setter
	@Getter
	private String jobType;

	@Setter
	@Getter
	private String masterPlanSn;

	@Setter
	@Getter
	private String masterPlanNcId;

	@Setter
	@Getter
	private String masterProductSn;

	@Setter
	@Getter
	private String masterProductName;

	@Setter
	@Getter
	private String planSn;

	@Setter
	@Getter
	private String planType;

	@Setter
	@Getter
	private String planRule;

	@Setter
	@Getter
	private String planQuantity;

	/**
	 * 送修数量
	 */
	@Setter
	@Getter
	private Integer sendRepairNum;

	// 下面为标准的PlanDay

	/**
	 * 状态
	 */
	@Setter
	@Getter
	private Integer state;

	/**
	 * 类型 0-普通 1-返工
	 */
	@Setter
	@Getter
	private Integer type;

    /**
     * 生产计划
     */
	@TableField("plan_id")
	private Long planId;
	/**
	 * 生产NCid
	 */
	@TableField("plan_nc_id")
	@Setter
	@Getter
	private String planNcId;
    /**
     * 计划数量
     */
	@TableField("plan_num")
	private BigDecimal planNum;
    /**
     * 实际产出数量
     */
	private BigDecimal num;
	/**
	 * 实际完成数量
	 */
	@TableField("num_finish")
	@Getter
	@Setter
	private BigDecimal numFinish;
    /**
     * 合格产品数量
     */
	@TableField("num_success")
	private BigDecimal numSuccess;
    /**
     * 报废数量
     */
	@TableField("num_scrap")
	private BigDecimal numScrap;
    /**
     * 返工数量
     */
	@TableField("num_repair")
	private BigDecimal numRepair;
    /**
     * 制造日期
     */
	@TableField("manufacture_date")
	private Date manufactureDate;
    /**
     * 制造数量
     */
	@TableField("manufacture_num")
	private BigDecimal manufactureNum;
    /**
     * 制造人员
     */
	@TableField("manufacture_staffs")
	private String manufactureStaffs;
    /**
     * 上班工时
     */
	@TableField("work_time")
	private BigDecimal workTime;
    /**
     * 加班工时
     */
	@TableField("work_overtime")
	private BigDecimal workOvertime;
    /**
     * 异常工时
     */
	@TableField("work_errortime")
	private BigDecimal workErrortime;
    /**
     * 品质目标
     */
	@TableField("tag_quality")
	private String tagQuality;
    /**
     * 制造总工时
     */
	@TableField("manufacture_times")
	private BigDecimal manufactureTimes;
    /**
     * 关联工序
     */
	@TableField("job_id")
	private Long jobId;

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public BigDecimal getPlanNum() {
		return planNum;
	}

	public void setPlanNum(BigDecimal planNum) {
		this.planNum = planNum;
	}
	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}
	public BigDecimal getNumSuccess() {
		return numSuccess;
	}

	public void setNumSuccess(BigDecimal numSuccess) {
		this.numSuccess = numSuccess;
	}
	public BigDecimal getNumScrap() {
		return numScrap;
	}

	public void setNumScrap(BigDecimal numScrap) {
		this.numScrap = numScrap;
	}
	public BigDecimal getNumRepair() {
		return numRepair;
	}

	public void setNumRepair(BigDecimal numRepair) {
		this.numRepair = numRepair;
	}
	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	public BigDecimal getManufactureNum() {
		return manufactureNum;
	}

	public void setManufactureNum(BigDecimal manufactureNum) {
		this.manufactureNum = manufactureNum;
	}
	public String getManufactureStaffs() {
		return manufactureStaffs;
	}

	public void setManufactureStaffs(String manufactureStaffs) {
		this.manufactureStaffs = manufactureStaffs;
	}
	public BigDecimal getWorkTime() {
		return workTime;
	}

	public void setWorkTime(BigDecimal workTime) {
		this.workTime = workTime;
	}
	public BigDecimal getWorkOvertime() {
		return workOvertime;
	}

	public void setWorkOvertime(BigDecimal workOvertime) {
		this.workOvertime = workOvertime;
	}
	public BigDecimal getWorkErrortime() {
		return workErrortime;
	}

	public void setWorkErrortime(BigDecimal workErrortime) {
		this.workErrortime = workErrortime;
	}
	public String getTagQuality() {
		return tagQuality;
	}

	public void setTagQuality(String tagQuality) {
		this.tagQuality = tagQuality;
	}
	public BigDecimal getManufactureTimes() {
		return manufactureTimes;
	}

	public void setManufactureTimes(BigDecimal manufactureTimes) {
		this.manufactureTimes = manufactureTimes;
	}
	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}


	@Override
	public String toString() {
		return "PlanDay{" +
			", planId=" + planId +
			", planNum=" + planNum +
			", num=" + num +
			", numSuccess=" + numSuccess +
			", numScrap=" + numScrap +
			", numRepair=" + numRepair +
			", manufactureDate=" + manufactureDate +
			", manufactureNum=" + manufactureNum +
			", manufactureStaffs=" + manufactureStaffs +
			", workTime=" + workTime +
			", workOvertime=" + workOvertime +
			", workErrortime=" + workErrortime +
			", tagQuality=" + tagQuality +
			", manufactureTimes=" + manufactureTimes +
			", jobId=" + jobId +
			"}";
	}

	public PlanDay toPlanDay(){
		PlanDay planDay = new PlanDay();
		planDay.setId(this.id);
		planDay.setType(this.type);
		planDay.setPlanId(this.planId);
		planDay.setPlanNum(this.planNum);
		planDay.setNum(this.num);
		planDay.setNumSuccess(this.numSuccess);
		planDay.setNumScrap(this.numScrap);
		planDay.setNumRepair(this.numRepair);
		planDay.setManufactureDate(this.manufactureDate);
		planDay.setManufactureNum(this.manufactureNum);
		planDay.setManufactureStaffs(this.manufactureStaffs);
		planDay.setWorkTime(this.workTime);
		planDay.setWorkOvertime(this.workOvertime);
		planDay.setWorkErrortime(this.workErrortime);
		planDay.setTagQuality(this.tagQuality);
		planDay.setManufactureTimes(this.manufactureTimes);
		planDay.setJobId(this.jobId);
		planDay.setCreateDate(this.createDate);
		planDay.setCreateId(this.createId);
		planDay.setUpdateDate(this.updateDate);
		planDay.setUpdateId(this.updateId);
		planDay.setRemarks(this.remarks);
		planDay.setDelFlag(this.delFlag);
		planDay.setNumFinish(this.numFinish);
		planDay.setState(this.state);
		return planDay;
	}
}
