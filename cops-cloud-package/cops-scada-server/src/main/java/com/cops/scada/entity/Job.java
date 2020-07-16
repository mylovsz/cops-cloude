package com.cops.scada.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 作业（工艺）管理
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@TableName("scada_job")
public class Job extends DataEntity<Job> {

    private static final long serialVersionUID = 1L;

	/**
	 * 父节点ID
	 */
	@TableField("parent_id")
	@Setter
	@Getter
	private Long parentId;

	/**
	 * 父节点联集
	 */
	@TableField("parent_ids")
	@Setter
	@Getter
	private String parentIds;

	/**
	 * 层级深度
	 */
	@Setter
	@Getter
	private Integer level;

	/**
	 * 排序
	 */
	@Setter
	@Getter
	private Integer sort;

    /**
     * 产品ID
     */
	@TableField("product_id")
	private Long productId;
    /**
     * 生产计划
     */
	@TableField("plan_id")
	private Long planId;
    /**
     * 身份类型
     */
	@TableField("identity_type")
	private Integer identityType;
    /**
     * 类型
     */
	private Integer type;
    /**
     * 作业编号
     */
	private String sn;
    /**
     * 作业名称
     */
	private String name;
    /**
     * 加工要求
     */
	private String requirement;
    /**
     * 准备（辅助）工时
     */
	@TableField("subsidiary_time")
	private BigDecimal subsidiaryTime;
    /**
     * 加工工时
     */
	@TableField("process_time")
	private BigDecimal processTime;
    /**
     * 标准工时
     */
	@TableField("work_time")
	private BigDecimal workTime;
    /**
     * 单价
     */
	private BigDecimal price;
    /**
     * 作业介绍
     */
	private String introduction;
    /**
     * SOP附件
     */
	@TableField("sop_attachment")
	private String sopAttachment;
    /**
     * SOP附件名称
     */
	@TableField("sop_name")
	private String sopName;
    /**
     * 程序附件
     */
	@TableField("program_attachment")
	private String programAttachment;
    /**
     * 程序名称
     */
	@TableField("program_name")
	private String programName;
    /**
     * 附件
     */
	@TableField("other_attachment")
	private String otherAttachment;
    /**
     * 附件名称
     */
	@TableField("other_name")
	private String otherName;
    /**
     * 版本
     */
	private String version;
    /**
     * 状态
     */
	private Integer state;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public Integer getIdentityType() {
		return identityType;
	}

	public void setIdentityType(Integer identityType) {
		this.identityType = identityType;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	public BigDecimal getSubsidiaryTime() {
		return subsidiaryTime;
	}

	public void setSubsidiaryTime(BigDecimal subsidiaryTime) {
		this.subsidiaryTime = subsidiaryTime;
	}
	public BigDecimal getProcessTime() {
		return processTime;
	}

	public void setProcessTime(BigDecimal processTime) {
		this.processTime = processTime;
	}
	public BigDecimal getWorkTime() {
		return workTime;
	}

	public void setWorkTime(BigDecimal workTime) {
		this.workTime = workTime;
	}
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getSopAttachment() {
		return sopAttachment;
	}

	public void setSopAttachment(String sopAttachment) {
		this.sopAttachment = sopAttachment;
	}
	public String getSopName() {
		return sopName;
	}

	public void setSopName(String sopName) {
		this.sopName = sopName;
	}
	public String getProgramAttachment() {
		return programAttachment;
	}

	public void setProgramAttachment(String programAttachment) {
		this.programAttachment = programAttachment;
	}
	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getOtherAttachment() {
		return otherAttachment;
	}

	public void setOtherAttachment(String otherAttachment) {
		this.otherAttachment = otherAttachment;
	}
	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "Job{" +
			", productId=" + productId +
			", planId=" + planId +
			", identityType=" + identityType +
			", type=" + type +
			", sn=" + sn +
			", name=" + name +
			", requirement=" + requirement +
			", subsidiaryTime=" + subsidiaryTime +
			", processTime=" + processTime +
			", workTime=" + workTime +
			", price=" + price +
			", introduction=" + introduction +
			", sopAttachment=" + sopAttachment +
			", sopName=" + sopName +
			", programAttachment=" + programAttachment +
			", programName=" + programName +
			", otherAttachment=" + otherAttachment +
			", otherName=" + otherName +
			", version=" + version +
			", state=" + state +
			"}";
	}
}
