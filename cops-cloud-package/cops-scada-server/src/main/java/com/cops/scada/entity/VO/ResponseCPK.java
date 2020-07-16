package com.cops.scada.entity.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseCPK implements Serializable {

    /**
     * 标准下限
     */
    private String lower;
    /**
     * 标准上限
     */
    private String upper;
    /**
     * 标准偏差
     */
    private String stDev;

    /**
     * CP
     */
    private String cp;

    /**
     * 平均值
     */
    private String avage;

    /**
     * 最大值
     */
    private String max;

    /**
     * 最小值
     */
    private String min;

    /**
     * CPKU
     */
    private String cpkU;

    /**
     * CPKL
     */
    private String cpkL;

    /**
     * CPK
     */
    private String cpk;
}
