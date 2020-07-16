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
 * 跳流程产品
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@TableName("scada_lack_produce")
public class LackProduce extends DataEntity<LackProduce> {

    private static final long serialVersionUID = 1L;

    /**
     * 生产产品
     */
	@TableField("produce_id")
	private Long produceId;
    /**
     * 流程类型
     */
	private Integer type;
    /**
     * 当前测试点
     */
	@TableField("current_site")
	private String currentSite;
    /**
     * 未测试点
     */
	@TableField("lack_site")
	private String lackSite;
    /**
     * 跳站数量
     */
	@TableField("lack_num")
	private BigDecimal lackNum;
    /**
     * 站点测试员
     */
	@TableField("site_worker")
	private String siteWorker;
    /**
     * 站点信息
     */
	@TableField("site_info")
	private String siteInfo;

	@Setter
	@Getter
	private Date collectDate;
    /**
     * 状态
     */
	private Integer state;

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
	public String getCurrentSite() {
		return currentSite;
	}

	public void setCurrentSite(String currentSite) {
		this.currentSite = currentSite;
	}
	public String getLackSite() {
		return lackSite;
	}

	public void setLackSite(String lackSite) {
		this.lackSite = lackSite;
	}
	public BigDecimal getLackNum() {
		return lackNum;
	}

	public void setLackNum(BigDecimal lackNum) {
		this.lackNum = lackNum;
	}
	public String getSiteWorker() {
		return siteWorker;
	}

	public void setSiteWorker(String siteWorker) {
		this.siteWorker = siteWorker;
	}
	public String getSiteInfo() {
		return siteInfo;
	}

	public void setSiteInfo(String siteInfo) {
		this.siteInfo = siteInfo;
	}
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "LackProduce{" +
			", produceId=" + produceId +
			", type=" + type +
			", currentSite=" + currentSite +
			", lackSite=" + lackSite +
			", lackNum=" + lackNum +
			", siteWorker=" + siteWorker +
			", siteInfo=" + siteInfo +
			", state=" + state +
			"}";
	}
}
