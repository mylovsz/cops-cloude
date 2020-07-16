package com.cops.scada.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;

/**
 * <p>
 * 生产计划关联
 * </p>
 *
 * @author wanglm
 * @since 2020-04-08
 */
@TableName("scada_plan_link")
public class PlanLink extends DataEntity<PlanLink> {

    private static final long serialVersionUID = 1L;

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
