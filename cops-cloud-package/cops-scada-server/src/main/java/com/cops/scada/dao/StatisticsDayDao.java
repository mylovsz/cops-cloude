package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.scada.entity.VO.StatisticsDayVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StatisticsDayDao extends BaseMapper<StatisticsDayVO> {

    /**
     * 统计不同站别 指定时间内的数据条数
     * @param sexName 表名称
     * @param t1    开始时间
     * @param t2    结束时间
     * @param result 筛选pass
     * @return
     */
    List<StatisticsDayVO> getStatisticsDayCollect(@Param("sex") String sexName, @Param("t1")Date t1,@Param("t2")Date t2,@Param("result")String result);

    /**
     * 统计维修 指定时间内的数据条数
     * @param t1    开始时间
     * @param t2    结束时间
     * @return
     */
    List<StatisticsDayVO> getStatisticsDayRepairCollect( @Param("t1")Date t1,@Param("t2")Date t2);


    /**
     * 统计工单 指定时间内的数据条数
     * @param t1    开始时间
     * @param t2    结束时间
     * @return
     */
    List<StatisticsDayVO> getStatisticsDayPlanCollect( @Param("t1")Date t1,@Param("t2")Date t2);

}
