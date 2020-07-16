package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.PlanParameter;
import com.cops.scada.dao.PlanParameterDao;
import com.cops.scada.entity.VO.PlanParameterVO;
import com.cops.scada.service.PlanParameterService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 工单统计参数 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-03-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PlanParameterServiceImpl extends ServiceImpl<PlanParameterDao, PlanParameter> implements PlanParameterService {

    @Override
    public PlanParameter getPlanParameter(Long planId, Integer label) {
        PlanParameter temp = new PlanParameter();
        temp.setPlanId(planId);
        temp.setLabel(label);
        return baseMapper.selectOne(temp);
    }

    // 0-点火最小值
    // 1-点火最大值
    // 2-点火时长最小
    // 3-点火时长最大
    @Override
    public Double getHotVU(Long planId) {
        PlanParameter temp = getPlanParameter(planId,1);
        return Double.parseDouble(temp.getValue());
    }

    @Override
    public Double getHotVL(Long planId) {
        PlanParameter temp = getPlanParameter(planId,0);
        return Double.parseDouble(temp.getValue());
    }

    @Override
    public Double getHotTU(Long planId) {
        PlanParameter temp = getPlanParameter(planId,3);
        return Double.parseDouble(temp.getValue());
    }

    @Override
    public Double getHotTL(Long planId) {
        PlanParameter temp = getPlanParameter(planId,2);
        return Double.parseDouble(temp.getValue());
    }

    @Override
    public Page<PlanParameterVO> getPlanParameterVO(Page<PlanParameterVO> page, EntityWrapper<PlanParameterVO> wrapper) {
        return page.setRecords(this.baseMapper.getPlanParameterVO(page, wrapper));
    }
}
