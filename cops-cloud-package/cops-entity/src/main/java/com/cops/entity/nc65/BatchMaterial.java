package com.cops.entity.nc65;

import lombok.Data;

@Data
public class BatchMaterial {
    /**
     * 组织编码
     */
    private String orgsCode;
    /**
     * 生产订单号
     */
    private String pmoBillCode;
    /**
     * 物料编码
     */
    private String materialCode;
    /**
     * 类型
     */
    private String materialName;
    /**
     * 规格
     */
    private String materialSpec;
    /**
     * 出库日期
     */
    private String bizDate;
    /**
     * 生产日期
     */
    private String produceDate;
    /**
     * 实发数量
     */
    private String assistNum;
    /**
     * 批次号
     */
    private String batchCode;
    /**
     * 供应商
     */
    private String supplierName;
    /**
     * 仓库
     */
    private String stordocName;
    /**
     * 内部编码
     */
    private String innerCode;
    /**
     * 货位
     */
    private String rackName;
}
