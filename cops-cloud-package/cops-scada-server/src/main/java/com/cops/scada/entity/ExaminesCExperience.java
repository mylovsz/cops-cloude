package com.cops.scada.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;

/**
 * <p>
 * 老化数据内容
 * </p>
 *
 * @author wanglm
 * @since 2020-01-03
 */
@TableName("scada_examines_c_experience")
public class ExaminesCExperience extends DataEntity<ExaminesCExperience> {

    private static final long serialVersionUID = 1L;

    /**
     * 关联id
     */
	@TableField("examines_id")
	private Long examinesId;
    /**
     * 批次号
     */
	@TableField("meter_a")
	private String meterA;
    /**
     * 异常时间
     */
	@TableField("meter_b")
	private String meterB;
    /**
     * 结果字符串
     */
	@TableField("meter_c")
	private String meterC;
    /**
     * 结果
     */
	@TableField("meter_d")
	private String meterD;
    /**
     * 订单编号
     */
	@TableField("meter_e")
	private String meterE;
    /**
     * 失败数量
     */
	@TableField("meter_f")
	private String meterF;
    /**
     * 总数
     */
	@TableField("meter_g")
	private String meterG;
    /**
     * 型号
     */
	@TableField("meter_h")
	private String meterH;

	public Long getExaminesId() {
		return examinesId;
	}

	public void setExaminesId(Long examinesId) {
		this.examinesId = examinesId;
	}
	public String getMeterA() {
		return meterA;
	}

	public void setMeterA(String meterA) {
		this.meterA = meterA;
	}
	public String getMeterB() {
		return meterB;
	}

	public void setMeterB(String meterB) {
		this.meterB = meterB;
	}
	public String getMeterC() {
		return meterC;
	}

	public void setMeterC(String meterC) {
		this.meterC = meterC;
	}
	public String getMeterD() {
		return meterD;
	}

	public void setMeterD(String meterD) {
		this.meterD = meterD;
	}
	public String getMeterE() {
		return meterE;
	}

	public void setMeterE(String meterE) {
		this.meterE = meterE;
	}
	public String getMeterF() {
		return meterF;
	}

	public void setMeterF(String meterF) {
		this.meterF = meterF;
	}
	public String getMeterG() {
		return meterG;
	}

	public void setMeterG(String meterG) {
		this.meterG = meterG;
	}
	public String getMeterH() {
		return meterH;
	}

	public void setMeterH(String meterH) {
		this.meterH = meterH;
	}


	@Override
	public String toString() {
		return "ExaminesCExperience{" +
			", examinesId=" + examinesId +
			", meterA=" + meterA +
			", meterB=" + meterB +
			", meterC=" + meterC +
			", meterD=" + meterD +
			", meterE=" + meterE +
			", meterF=" + meterF +
			", meterG=" + meterG +
			", meterH=" + meterH +
			"}";
	}
}
