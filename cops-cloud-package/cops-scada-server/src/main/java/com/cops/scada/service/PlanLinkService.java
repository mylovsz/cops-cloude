package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.PlanLink;
import com.baomidou.mybatisplus.service.IService;
import com.cops.scada.entity.VO.PlanLinkSlaveVO;

import java.util.List;

/**
 * <p>
 * 生产计划关联 服务类
 * </p>
 *
 * @author wanglm
 * @since 2020-04-08
 */
public interface PlanLinkService extends IService<PlanLink> {

    /**
     * 通用分页查询
     * @param page
     * @param wrapper
     * @return
     */
    Page<PlanLinkSlaveVO> getPlanLinkSlaveVO(Page<PlanLinkSlaveVO> page, EntityWrapper<PlanLinkSlaveVO> wrapper);

    /**
     * 获取列表
     * @param wrapper
     * @return
     */
    List<PlanLinkSlaveVO> listPlanLink(EntityWrapper<PlanLinkSlaveVO> wrapper);

    /**
     * 获取列表
     * @param masterPlanId
     * @return
     */
    List<PlanLinkSlaveVO> listPlanLink(Long masterPlanId);

    /**
     * 获取列表
     * @param masterPlanId 主计划的id
     * @param slavePlanId 子计划的id
     * @return
     */
    PlanLinkSlaveVO getPlanLinkSlaveVO(Long masterPlanId, Long slavePlanId);
}
