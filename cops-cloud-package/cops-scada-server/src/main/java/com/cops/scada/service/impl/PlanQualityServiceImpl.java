package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cops.scada.dao.PlanQualityDao;
import com.cops.scada.entity.VO.PlanProductVO;
import com.cops.scada.entity.VO.PlanQualityVO;
import com.cops.scada.service.PlanQualityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 生产计划 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-03-14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PlanQualityServiceImpl extends ServiceImpl<PlanQualityDao, PlanQualityVO> implements PlanQualityService {

    @Override
    public Page<PlanQualityVO> getPlanQualityVO(Page<PlanQualityVO> page, EntityWrapper<PlanQualityVO> wrapper) {
        return page.setRecords(this.baseMapper.getPlanQualityVO(page, wrapper));
    }
}
