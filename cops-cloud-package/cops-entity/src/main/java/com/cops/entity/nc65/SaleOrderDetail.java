package com.cops.entity.nc65;

import lombok.Data;

@Data
public class SaleOrderDetail {
    /**
     * 物料主键
     */
    private String materialId;
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
     * 订单需求数量
     */
    private String saleOrderNum;
    /**
     * 单据日期
     */
    private String saleOrderBillDate;
    /**
     * 到货日期
     */
    private String saleOrderReceiveDate;
    /**
     * 发货日期
     */
    private String saleOrderSendDate;
}
