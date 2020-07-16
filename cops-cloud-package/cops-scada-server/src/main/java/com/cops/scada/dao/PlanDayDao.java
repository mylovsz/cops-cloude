package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.PlanDay;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cops.scada.entity.VO.PlanDayVO;
import com.cops.scada.entity.VO.PlanProductVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 日计划统计 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
public interface PlanDayDao extends BaseMapper<PlanDay> {

    Integer getPlanDayForMakeNum(@Param("ew")EntityWrapper<PlanDay> wrapper);

    List<PlanDayVO> getPlanDayVO(@Param("ew")EntityWrapper<PlanDayVO> wrapper);

    List<PlanDayVO> getPagePlanDayVO(Page<PlanDayVO> page, @Param("ew")EntityWrapper<PlanDayVO> wrapper);
}
