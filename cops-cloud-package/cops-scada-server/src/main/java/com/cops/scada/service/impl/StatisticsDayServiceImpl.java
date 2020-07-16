package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cops.scada.dao.StatisticsDayDao;
import com.cops.scada.entity.VO.StatisticsDayVO;
import com.cops.scada.service.StatisticsDayService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatisticsDayServiceImpl extends ServiceImpl<StatisticsDayDao, StatisticsDayVO> implements StatisticsDayService {

    /**
     * @param sexName 表名称
     * @param t1      开始时间
     * @param t2      结束时间
     * @param result  筛选pass
     * @return
     */
    @Override
    public List<StatisticsDayVO> getStatisticsDayCollect(String sexName,Date t1,Date t2,String result) {
        return this.baseMapper.getStatisticsDayCollect(sexName,t1,t2,result);
    }


    /**
     * @param t1      开始时间
     * @param t2      结束时间
     * @return
     */
    @Override
    public List<StatisticsDayVO> getStatisticsDayRepairCollect(Date t1,Date t2) {
        return this.baseMapper.getStatisticsDayRepairCollect(t1,t2);
    }

    /**
     * 统计工单 指定时间内的数据条数
     * @param t1    开始时间
     * @param t2    结束时间
     * @return
     */
    @Override
    public List<StatisticsDayVO> getStatisticsDayPlanCollect(Date t1,Date t2){
        return this.baseMapper.getStatisticsDayPlanCollect(t1,t2);
    }
}
