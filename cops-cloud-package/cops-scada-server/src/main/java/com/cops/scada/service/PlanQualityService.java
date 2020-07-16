package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cops.scada.entity.VO.PlanQualityVO;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 生产计划 服务类
 * </p>
 *
 * @author wanglm
 * @since 2019-03-14
 */
public interface PlanQualityService extends IService<PlanQualityVO> {

    Page<PlanQualityVO> getPlanQualityVO(Page<PlanQualityVO> page, EntityWrapper<PlanQualityVO> wrapper);
}
