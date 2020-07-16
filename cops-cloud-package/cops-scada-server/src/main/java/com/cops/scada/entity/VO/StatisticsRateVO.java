package com.cops.scada.entity.VO;

import lombok.Data;

@Data
public class StatisticsRateVO {
    /**
     * 产品数据覆盖率
     */
    private String productDataCoverRate;

    /**
     * 产品直通率
     */
    private String productPerfectRate;

    /**
     * 产品不良率
     */
    private String productErrorRate;

    /**
     * 产品完成率
     */
    private String productCompleteRate;

    /**
     * 开始统计时间
     */
    private String startDate;

    /**
     * 结束统计时间
     */
    private String endDate;
}
