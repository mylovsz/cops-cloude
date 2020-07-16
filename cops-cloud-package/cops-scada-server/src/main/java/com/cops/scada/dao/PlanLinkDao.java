package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.PlanLink;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cops.scada.entity.VO.PlanDayVO;
import com.cops.scada.entity.VO.PlanLinkSlaveVO;
import com.cops.scada.entity.VO.StatisticsPlanVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 生产计划关联 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2020-04-08
 */
public interface PlanLinkDao extends BaseMapper<PlanLink> {

    /**
     * 获取子订单的详细
     * @param page
     * @param wrapper
     * @return
     */
    List<PlanLinkSlaveVO> getPlanLinkSlaveVO(Page<PlanLinkSlaveVO> page, @Param("ew") EntityWrapper<PlanLinkSlaveVO> wrapper);

    List<PlanLinkSlaveVO> getPlanLinkSlaveVO(@Param("ew") EntityWrapper<PlanLinkSlaveVO> wrapper);
}
