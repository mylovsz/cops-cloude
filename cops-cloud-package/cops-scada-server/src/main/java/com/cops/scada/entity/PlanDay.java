package com.cops.scada.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 日计划统计
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@TableName("scada_plan_day")
public class PlanDay extends DataEntity<PlanDay> {

    private static final long serialVersionUID = 1L;

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
}
