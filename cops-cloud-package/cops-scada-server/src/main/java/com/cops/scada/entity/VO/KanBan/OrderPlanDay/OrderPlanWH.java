package com.cops.scada.entity.VO.KanBan.OrderPlanDay;

import lombok.Getter;
import lombok.Setter;

/**
 * 日计划工时试图实体
 */
@Getter
@Setter
public class OrderPlanWH
{
    private String lineType;

    private String nCid;

    private String pDTsn;

    private String wHNormal;

    private String wHInvest;

    private String wHOutput;

    private String prolificacy;

    private String wHShift;

    private String wHUnshift;

    private String prolificacyRate;

}