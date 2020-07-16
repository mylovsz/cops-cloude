package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.VO.PlanQualityVO;
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
public interface PlanQualityDao extends BaseMapper<PlanQualityVO> {

    List<PlanQualityVO> getPlanQualityVO(Page<PlanQualityVO> page, @Param("ew") EntityWrapper<PlanQualityVO> wrapper);
}
