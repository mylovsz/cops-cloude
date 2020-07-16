package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.VO.WorkDetailVO;
import com.cops.scada.entity.VO.statistic.TotalWorkDetailVO;
import com.cops.scada.entity.WorkDetail;
import com.cops.scada.dao.WorkDetailDao;
import com.cops.scada.service.WorkDetailService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 工时详情 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2020-04-08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WorkDetailServiceImpl extends ServiceImpl<WorkDetailDao, WorkDetail> implements WorkDetailService {

    @Override
    public Page<WorkDetailVO> getWorkDetailVO(Page<WorkDetailVO> page, EntityWrapper<WorkDetailVO> wrapper) {
        return page.setRecords(this.baseMapper.getWorkDetailDao(page, wrapper));
    }

    @Override
    public WorkDetailVO getWorkDetailVO(Long id) {
        EntityWrapper<WorkDetailVO> wrapper = new EntityWrapper<>();
        wrapper.eq("work_detail.id", id);
        List<WorkDetailVO> list = this.baseMapper.getWorkDetailDao(wrapper);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public TotalWorkDetailVO getTotalWorkDetailVO(Long planDayId) {
        EntityWrapper<WorkDetailVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan_day_id", planDayId)
                .eq("state", 1);
        return this.baseMapper.getTotalWorkDetailVO(wrapper);
    }

    @Override
    public List<WorkDetailVO> listForGoingByPlanDay(Long planDayId) {
        EntityWrapper<WorkDetailVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan_day_id", planDayId)
                .eq("state", 0);
        return null;
    }
}
