package com.cops.scada.entity.VO.KanBan.OrderPlanDay;


import lombok.Getter;
import lombok.Setter;

/**
 * 日计划工时汇总视图实体
 */
@Getter
@Setter
public class OrderPlanWHTotal
{
    /**
     * 应出勤工时
     */
    private double wHShould;

    /**
     * 实出勤工时
     */
    private double wHReal;
    /**
     * 借入工时
     */
    private double wHBorrow;
    /**
     * 借出工时
     */
    private double wHLend;
    /**
     * 投入工时
     */
    private double wHInvestTotal;
    /**
     * 异常工时
     */
    private double wHErrorTotal;
    /**
     * 总生产力
     */
    private double prolificacyTotal;
    /**
     * 总生产效率
     */
    private double prolificacyTotalRate;
}

