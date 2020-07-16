package com.cops.library.until;

import org.apache.commons.lang.StringUtils;

public class NC65DataConverter {

    /**
     * 将MES系统中的工单号，转换为NC系统的工单单号
     * 转换规则：%15_____418
     *  planSn前2位，对应15，后3位对应418
     * @param planSn
     * @return
     */
    public static String productionOrderSnToId(String planSn){
        if(StringUtils.isNotBlank(planSn) && planSn.length()>=5){
            return planSn.substring(0, 2)+"%_____"+planSn.substring(2, 5);
        }
        return null;
    }
}
