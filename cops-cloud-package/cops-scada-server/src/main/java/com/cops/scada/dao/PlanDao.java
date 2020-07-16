package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.Plan;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cops.scada.entity.Product;
import com.cops.scada.entity.VO.PlanProductVO;
import com.cops.scada.entity.VO.PlanQualityDetailVO;
import com.cops.scada.entity.VO.StatisticsPlanVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 生产计划 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2019-03-14
 */
public interface PlanDao extends BaseMapper<Plan> {

    PlanQualityDetailVO getPlanQualityDetailVO(@Param("planId")Long planId);

    /**
     * 查询订单的统计信息
     * @param page
     * @param wrapper
     * @return
     */
    StatisticsPlanVO getStatisticsPlanVO(@Param("ew")EntityWrapper<StatisticsPlanVO> wrapper);

    /**
     * 查询订单的统计信息
     * @param page
     * @param wrapper
     * @return
     */
    StatisticsPlanVO getStatisticsPlanVOAndProduct(@Param("ew")EntityWrapper<StatisticsPlanVO> wrapper);

    Boolean exists(@Param("ew") EntityWrapper<Product> wrapper);

    List<PlanProductVO> getPlanProduct(Page<PlanProductVO> page, @Param("ew")EntityWrapper<PlanProductVO> wrapper);
}
