package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.PlanLink;
import com.cops.scada.dao.PlanLinkDao;
import com.cops.scada.entity.VO.PlanLinkSlaveVO;
import com.cops.scada.service.PlanLinkService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 生产计划关联 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2020-04-08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PlanLinkServiceImpl extends ServiceImpl<PlanLinkDao, PlanLink> implements PlanLinkService {

    @Override
    public Page<PlanLinkSlaveVO> getPlanLinkSlaveVO(Page<PlanLinkSlaveVO> page, EntityWrapper<PlanLinkSlaveVO> wrapper) {
        return page.setRecords(this.baseMapper.getPlanLinkSlaveVO(page, wrapper));
    }

    @Override
    public List<PlanLinkSlaveVO> listPlanLink(EntityWrapper<PlanLinkSlaveVO> wrapper) {
        return this.baseMapper.getPlanLinkSlaveVO(wrapper);
    }

    @Override
    public PlanLinkSlaveVO getPlanLinkSlaveVO(Long masterPlanId, Long slavePlanId) {
        EntityWrapper<PlanLinkSlaveVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan_link.master_plan_id", masterPlanId);
        wrapper.eq("plan_link.slave_plan_id", slavePlanId);
        List<PlanLinkSlaveVO> list = this.listPlanLink(wrapper);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<PlanLinkSlaveVO> listPlanLink(Long masterPlanId) {
        EntityWrapper<PlanLinkSlaveVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan_link.master_plan_id", masterPlanId);
        return this.listPlanLink(wrapper);
    }
}
