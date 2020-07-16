package com.cops.scada.entity.VO;

import lombok.Data;

import java.util.Date;

@Data
public class QualityExaminesVO implements Comparable<QualityExaminesVO> {

    /**
     * 收集时间
     */
    private Date collectDate;

    /**
     * 工单号
     */
    private String planSn;

    /**
     * 产品型号
     */
    private String productSn;

    /**
     * 总测试条数
     */
    private Long tocalCount;

    /**
     * 测试产品个数
     */
    private Long produceCount;

    /**
     * 测试产品异常个数
     */
    private Long produceError;

    @Override
    public int compareTo(QualityExaminesVO o) {
        return o.getProduceError().compareTo(this.getProduceError());
    }
}
