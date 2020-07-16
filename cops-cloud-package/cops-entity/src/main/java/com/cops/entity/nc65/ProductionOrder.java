package com.cops.entity.nc65;

import lombok.Data;

/**
 * 生产订单
 */
@Data
public class ProductionOrder {
    /**
     * 生产订单主键
     */
    private String moId;
    /**
     * 计划订单号
     */
    private String ploBillCode;
    /**
     * 产品编码
     */
    private String materialCode;
    /**
     * 产品名称
     */
    private String materialName;
    /**
     * 产品版本
     */
    private String materialVersion;
    /**
     * 生产部门
     */
    private String deptName;
    /**
     * 生产线
     */
    private String wkName;
    /**
     * BOM主键
     */
    private String bomId;
    /**
     * BOM版本
     */
    private String bomVersion;
    /**
     * pmohid
     */
    private String pmoId;
    /**
     * 生产订单号
     */
    private String pmoBillCode;
    /**
     * 生产订单状态
     * 0=自由，1=审批，2=提交，3=审批中，4=审批不通过
     */
    private String pmoBillStatus;
    /**
     * 补单类型
     * 0=普通订单，1=订单返工补单，2=报废补单，3=工序返工补单
     */
    private String moBillType;
    /**
     * 数量
     */
    private String num;
    /**
     * 合格入库数量
     */
    private String qualifiedNum;
    /**
     * 计划产出数量
     */
    private String planOutputNum;
    /**
     * 计划投入数量
     */
    private String planPutNum;
    /**
     * 完工数量
     */
    private String completeNum;
    /**
     * 计划开始日期
     */
    private String planStartTime;
    /**
     * 计划完成日期
     */
    private String planEndTime;
    /**
     * 关闭日期
     */
    private String closedTime;
    /**
     * 实际开始日期
     */
    private String actStartTime;
    /**
     * 实际结束日期
     */
    private String actEndTime;
    /**
     * 预计完工时间
     */
    private String willEndTime;
    /**
     * 生产批次
     */
    private String batchCode;
}
