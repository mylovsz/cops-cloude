package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.PlanParameter;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cops.scada.entity.VO.PlanParameterVO;
import com.cops.scada.entity.VO.PlanProductVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 工单统计参数 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2019-03-21
 */
public interface PlanParameterDao extends BaseMapper<PlanParameter> {
    List<PlanParameterVO> getPlanParameterVO(Page<PlanParameterVO> page, @Param("ew") EntityWrapper<PlanParameterVO> wrapper);
}
