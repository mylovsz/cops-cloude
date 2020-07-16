package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.Kanban;
import com.cops.scada.dao.KanbanDao;
import com.cops.scada.entity.VO.PlanDayVO;
import com.cops.scada.entity.VO.WorkDetailVO;
import com.cops.scada.service.KanbanService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cops.scada.service.PlanDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 看板配置 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2020-04-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class KanbanServiceImpl extends ServiceImpl<KanbanDao, Kanban> implements KanbanService {

    @Autowired
    private PlanDayService planDayService;

    @Override
    public Page<PlanDayVO> getPagePlanDayVO(Page<PlanDayVO> page, EntityWrapper<PlanDayVO> wrapper) {
        return page.setRecords(this.baseMapper.getPagePlanDayVO(page, wrapper));
    }


    @Override
    public List<WorkDetailVO> getWorkDetailVO(EntityWrapper<WorkDetailVO> wrapper) {
        return this.baseMapper.getWorkDetailVO(wrapper);
    }
}
