package com.cops.entity.nc65;

import lombok.Data;

@Data
public class SaleOrder {

    /**
     * 主键
     */
    private String saleOrderId;

    /**
     * NC系统单据号
     */
    private String billCode;
    /**
     * 客户订单号
     */
    private String customerCode;
    /**
     * 客户
     */
    private String customerName;
    /**
     * 客户简码
     */
    private String customerShortName;
    /**
     * 业务PI
     */
    private String pi;
    /**
     * 需求数量
     */
    private String totalNum;
    /**
     * 订单状态
     */
    private String status;
    /**
     * 业务员
     */
    private String saleUser;
    /**
     * 制单人
     */
    private String billMaker;
    /**
     * 制单日期
     */
    private String makeDate;
    /**
     * 单据日期
     */
    private String billDate;
    /**
     * 审批人
     */
    private String approverUser;
    /**
     * 备注
     */
    private String note;
}
