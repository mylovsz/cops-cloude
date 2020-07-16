package com.cops.scada.entity.VO.KanBan.OrderPlanDay;


import lombok.Getter;
import lombok.Setter;

/**
 * 日计划排产视图实体
 */
@Getter
@Setter
public class OrderPlanDay
{
    private String lineType;

    private String nCid;

    private String pDTsn;

    private String orderTotal;

    private String orderOKCount;

    private String orderOKRate;

    private String planDayTotal;

    private String planDayCount;

    private String planDayOkRate;

    private String orderNGCount;

    private String orderNGRate;

    private String state;

    private String remark;
}
