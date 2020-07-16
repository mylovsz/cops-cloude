package com.cops.scada.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;

/**
 * <p>
 * 初检耐压
 * </p>
 *
 * @author wanglm
 * @since 2019-03-20
 */
@TableName("scada_examines_d")
public class ExaminesD extends DataEntity<ExaminesD> {

    private static final long serialVersionUID = 1L;

    /**
     * 生产产品
     */
	@TableField("produce_id")
	private Long produceId;
    /**
     * 类型
     */
	private Integer type;
    /**
     * meter_a
     */
	@TableField("meter_a")
	private String meterA;
    /**
     * meter_b
     */
	@TableField("meter_b")
	private String meterB;
    /**
     * meter_c
     */
	@TableField("meter_c")
	private String meterC;
    /**
     * meter_d
     */
	@TableField("meter_d")
	private String meterD;
    /**
     * meter_e
     */
	@TableField("meter_e")
	private String meterE;
    /**
     * meter_f
     */
	@TableField("meter_f")
	private String meterF;
    /**
     * meter_g
     */
	@TableField("meter_g")
	private String meterG;
    /**
     * meter_h
     */
	@TableField("meter_h")
	private String meterH;
    /**
     * meter_i
     */
	@TableField("meter_i")
	private String meterI;
    /**
     * meter_j
     */
	@TableField("meter_j")
	private String meterJ;
    /**
     * meter_k
     */
	@TableField("meter_k")
	private String meterK;
    /**
     * meter_l
     */
	@TableField("meter_l")
	private String meterL;
    /**
     * meter_m
     */
	@TableField("meter_m")
	private String meterM;
    /**
     * meter_n
     */
	@TableField("meter_n")
	private String meterN;
    /**
     * meter_o
     */
	@TableField("meter_o")
	private String meterO;
    /**
     * meter_p
     */
	@TableField("meter_p")
	private String meterP;
    /**
     * meter_q
     */
	@TableField("meter_q")
	private String meterQ;
    /**
     * meter_r
     */
	@TableField("meter_r")
	private String meterR;
    /**
     * meter_s
     */
	@TableField("meter_s")
	private String meterS;
    /**
     * meter_t
     */
	@TableField("meter_t")
	private String meterT;
    /**
     * 收集时间
     */
	@TableField("collect_date")
	private Date collectDate;
    /**
     * 结果
     */
	private String result;

	public Long getProduceId() {
		return produceId;
	}

	public void setProduceId(Long produceId) {
		this.produceId = produceId;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
	public String getMeterI() {
		return meterI;
	}

	public void setMeterI(String meterI) {
		this.meterI = meterI;
	}
	public String getMeterJ() {
		return meterJ;
	}

	public void setMeterJ(String meterJ) {
		this.meterJ = meterJ;
	}
	public String getMeterK() {
		return meterK;
	}

	public void setMeterK(String meterK) {
		this.meterK = meterK;
	}
	public String getMeterL() {
		return meterL;
	}

	public void setMeterL(String meterL) {
		this.meterL = meterL;
	}
	public String getMeterM() {
		return meterM;
	}

	public void setMeterM(String meterM) {
		this.meterM = meterM;
	}
	public String getMeterN() {
		return meterN;
	}

	public void setMeterN(String meterN) {
		this.meterN = meterN;
	}
	public String getMeterO() {
		return meterO;
	}

	public void setMeterO(String meterO) {
		this.meterO = meterO;
	}
	public String getMeterP() {
		return meterP;
	}

	public void setMeterP(String meterP) {
		this.meterP = meterP;
	}
	public String getMeterQ() {
		return meterQ;
	}

	public void setMeterQ(String meterQ) {
		this.meterQ = meterQ;
	}
	public String getMeterR() {
		return meterR;
	}

	public void setMeterR(String meterR) {
		this.meterR = meterR;
	}
	public String getMeterS() {
		return meterS;
	}

	public void setMeterS(String meterS) {
		this.meterS = meterS;
	}
	public String getMeterT() {
		return meterT;
	}

	public void setMeterT(String meterT) {
		this.meterT = meterT;
	}
	public Date getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}


	@Override
	public String toString() {
		return "ExaminesD{" +
			", produceId=" + produceId +
			", type=" + type +
			", meterA=" + meterA +
			", meterB=" + meterB +
			", meterC=" + meterC +
			", meterD=" + meterD +
			", meterE=" + meterE +
			", meterF=" + meterF +
			", meterG=" + meterG +
			", meterH=" + meterH +
			", meterI=" + meterI +
			", meterJ=" + meterJ +
			", meterK=" + meterK +
			", meterL=" + meterL +
			", meterM=" + meterM +
			", meterN=" + meterN +
			", meterO=" + meterO +
			", meterP=" + meterP +
			", meterQ=" + meterQ +
			", meterR=" + meterR +
			", meterS=" + meterS +
			", meterT=" + meterT +
			", collectDate=" + collectDate +
			", result=" + result +
			"}";
	}
}
