package com.cops.scada.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;

/**
 * <p>
 * 生产产品
 * </p>
 *
 * @author wanglm
 * @since 2019-03-19
 */
@TableName("scada_produce")
public class Produce extends DataEntity<Produce> {

    private static final long serialVersionUID = 1L;

    /**
     * 生产编号
     */
	private String sn;
    /**
     * 生产计划
     */
	@TableField("plan_id")
	private Long planId;
    /**
     * 客户编码
     */
	@TableField("customer_sn")
	private String customerSn;
    /**
     * 初检耐压
     */
	@TableField("result_a")
	private Integer resultA;
    /**
     * 初检
     */
	@TableField("result_b")
	private Integer resultB;
    /**
     * 老化
     */
	@TableField("result_c")
	private Integer resultC;
    /**
     * 终检耐压
     */
	@TableField("result_d")
	private Integer resultD;
    /**
     * 点火
     */
	@TableField("result_e")
	private Integer resultE;
    /**
     * 终检
     */
	@TableField("result_f")
	private Integer resultF;
    /**
     * 包装
     */
	@TableField("result_g")
	private Integer resultG;
    /**
     * 备用
     */
	@TableField("result_h")
	private Integer resultH;
    /**
     * 备用
     */
	@TableField("result_i")
	private Integer resultI;
    /**
     * 备用
     */
	@TableField("result_l")
	private Integer resultL;
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
	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public String getCustomerSn() {
		return customerSn;
	}

	public void setCustomerSn(String customerSn) {
		this.customerSn = customerSn;
	}
	public Integer getResultA() {
		return resultA;
	}

	public void setResultA(Integer resultA) {
		this.resultA = resultA;
	}
	public Integer getResultB() {
		return resultB;
	}

	public void setResultB(Integer resultB) {
		this.resultB = resultB;
	}
	public Integer getResultC() {
		return resultC;
	}

	public void setResultC(Integer resultC) {
		this.resultC = resultC;
	}
	public Integer getResultD() {
		return resultD;
	}

	public void setResultD(Integer resultD) {
		this.resultD = resultD;
	}
	public Integer getResultE() {
		return resultE;
	}

	public void setResultE(Integer resultE) {
		this.resultE = resultE;
	}
	public Integer getResultF() {
		return resultF;
	}

	public void setResultF(Integer resultF) {
		this.resultF = resultF;
	}
	public Integer getResultG() {
		return resultG;
	}

	public void setResultG(Integer resultG) {
		this.resultG = resultG;
	}
	public Integer getResultH() {
		return resultH;
	}

	public void setResultH(Integer resultH) {
		this.resultH = resultH;
	}
	public Integer getResultI() {
		return resultI;
	}

	public void setResultI(Integer resultI) {
		this.resultI = resultI;
	}
	public Integer getResultL() {
		return resultL;
	}

	public void setResultL(Integer resultL) {
		this.resultL = resultL;
	}
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "Produce{" +
			", sn=" + sn +
			", planId=" + planId +
			", customerSn=" + customerSn +
			", resultA=" + resultA +
			", resultB=" + resultB +
			", resultC=" + resultC +
			", resultD=" + resultD +
			", resultE=" + resultE +
			", resultF=" + resultF +
			", resultG=" + resultG +
			", resultH=" + resultH +
			", resultI=" + resultI +
			", resultL=" + resultL +
			", state=" + state +
			"}";
	}
}
