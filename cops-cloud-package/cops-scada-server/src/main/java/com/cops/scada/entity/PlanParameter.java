package com.cops.scada.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;

/**
 * <p>
 * 工单统计参数
 * </p>
 *
 * @author wanglm
 * @since 2019-03-21
 */
@TableName("scada_plan_parameter")
public class PlanParameter extends DataEntity<PlanParameter> {

    private static final long serialVersionUID = 1L;

    /**
     * 生产计划
     */
	@TableField("plan_id")
	private Long planId;
    /**
     * 名称
     */
	private Integer label;
    /**
     * 值
     */
	private String value;
    /**
     * 描述
     */
	private String description;

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public Integer getLabel() {
		return label;
	}

	public void setLabel(Integer label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "PlanParameter{" +
			", planId=" + planId +
			", label=" + label +
			", value=" + value +
			", description=" + description +
			"}";
	}
}
