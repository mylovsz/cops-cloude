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
 * 生产计划
 * </p>
 *
 * @author wanglm
 * @since 2019-03-14
 */
@TableName("scada_plan")
public class Plan extends DataEntity<Plan> {

    private static final long serialVersionUID = 1L;

    /**
     * 生产工单号
     */
	private String sn;

	/**
	 * NC关联字段
	 */
	@TableField("nc_id")
	@Setter
	@Getter
	private String ncId;

	/**
	 * 计划类型
	 */
	@Setter
	@Getter
	private Integer type;
	/**
	 * 计划规则
	 */
	@Setter
	@Getter
	private Integer rule;

    /**
     * 关联产品
     */
	@TableField("product_id")
	private Long productId;

    /**
     * 投产数量
     */
	private Long quantity;
    /**
     * 标签范围
     */
	@TableField("lable_range")
	private String lableRange;
    /**
     * 实际开始日期
     */
	@TableField("start_date")
	private Date startDate;
    /**
     * 实际结束日期
     */
	@TableField("end_date")
	private Date endDate;
    /**
     * 目标开始日期
     */
	@TableField("tag_start_date")
	private Date tagStartDate;
    /**
     * 目标结束日期
     */
	@TableField("tag_end_date")
	private Date tagEndDate;
    /**
     * 状态
     */
	private Integer state;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public String getLableRange() {
		return lableRange;
	}

	public void setLableRange(String lableRange) {
		this.lableRange = lableRange;
	}
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getTagStartDate() {
		return tagStartDate;
	}

	public void setTagStartDate(Date tagStartDate) {
		this.tagStartDate = tagStartDate;
	}
	public Date getTagEndDate() {
		return tagEndDate;
	}

	public void setTagEndDate(Date tagEndDate) {
		this.tagEndDate = tagEndDate;
	}
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "Plan{" +
			", sn=" + sn +
			", productId=" + productId +
			", quantity=" + quantity +
			", lableRange=" + lableRange +
			", startDate=" + startDate +
			", endDate=" + endDate +
			", tagStartDate=" + tagStartDate +
			", tagEndDate=" + tagEndDate +
			", state=" + state +
			"}";
	}
}
