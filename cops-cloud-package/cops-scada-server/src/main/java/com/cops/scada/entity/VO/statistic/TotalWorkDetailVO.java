package com.cops.scada.entity.VO.statistic;

import lombok.Getter;
import lombok.Setter;

/**
 * 工时相关的统计表
 *
 * @Author: wangermao
 * @Date: 2020-05-06 15:41
 * @Version: V1.0
 */
@Getter
@Setter
public class TotalWorkDetailVO {
    /**
     * 总工作时间（人*分钟）
     */
    private Long totalWorkTime;

    /**
     * 总的加工数量
     */
    private Long totalQuantityCount;

    /**
     * 加工总人数
     */
    private Long totalWorkUserNum;

    @Override
    public String toString(){
        return "总投入工时："+totalWorkTime
                + " 加工数量："+totalQuantityCount;
    }
}
