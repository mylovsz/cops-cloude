package com.cops.scada.entity.VO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

/**
 * <p>
 * 生产计划关联
 * </p>
 *
 * @author wanglm
 * @since 2020-04-08
 */
@TableName("scada_plan_link")
@Getter
@Setter
public class PlanLinkSlaveVO extends DataEntity<PlanLinkSlaveVO> {

    private static final long serialVersionUID = 1L;

    // slave 相关内容
	private String slavePlanSn;

	private String slaveProductSn;

	private Long slavePlanQuantity;

	private String slavePlanLableRange;

	private Date slavePlanTagEndDate;

	private Integer slavePlanState;

	private String slavePlanNcId;

	private Integer slavePlanType;

	private Integer slavePlanRule;
	// end slave 相关内容

    /**
     * 主生产计划
     */
	@TableField("master_plan_id")
	private Long masterPlanId;
    /**
     * 半成品生产计划
     */
	@TableField("slave_plan_id")
	private Long slavePlanId;
    /**
     * 注释
     */
	private String note;

	public Long getMasterPlanId() {
		return masterPlanId;
	}

	public void setMasterPlanId(Long masterPlanId) {
		this.masterPlanId = masterPlanId;
	}
	public Long getSlavePlanId() {
		return slavePlanId;
	}

	public void setSlavePlanId(Long slavePlanId) {
		this.slavePlanId = slavePlanId;
	}
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


	@Override
	public String toString() {
		return "PlanLink{" +
			", masterPlanId=" + masterPlanId +
			", slavePlanId=" + slavePlanId +
			", note=" + note +
			"}";
	}
}
