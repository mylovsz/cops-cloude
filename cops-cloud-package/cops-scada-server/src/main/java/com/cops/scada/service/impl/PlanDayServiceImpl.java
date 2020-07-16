package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.PlanDay;
import com.cops.scada.dao.PlanDayDao;
import com.cops.scada.entity.VO.PlanDayVO;
import com.cops.scada.service.PlanDayService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 日计划统计 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PlanDayServiceImpl extends ServiceImpl<PlanDayDao, PlanDay> implements PlanDayService {

    @Override
    public List<PlanDayVO> getPlanDayVOForDay(Date date, Long planId, String jobSn) {
        // 计算一天的开始于结束
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date start = calendar.getTime();

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, -1);

        Date end = calendar.getTime();

        EntityWrapper<PlanDayVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan_day.del_flag", false);
        wrapper.eq("job.del_flag", false);
        wrapper.eq("plan.del_flag", false);
        wrapper.eq("plan.id", planId);
        wrapper.eq("job.sn", jobSn);
        wrapper.between("plan_day.manufacture_date", start, end);


        return this.baseMapper.getPlanDayVO(wrapper);
    }

    @Override
    public PlanDayVO getPlanDay(Long id) {
        EntityWrapper<PlanDayVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan_day.del_flag", false);
        wrapper.eq("job.del_flag", false);
        wrapper.eq("plan.del_flag", false);
        wrapper.eq("plan_day.id", id);
        List<PlanDayVO> planDays = this.baseMapper.getPlanDayVO(wrapper);
        if(planDays != null && planDays.size()>0)
            return planDays.get(0);
        return null;
    }

    @Override
    public Boolean addPlanDay(PlanDay planDay) {
        return this.baseMapper.insert(planDay) > 0 ? true : false;
    }

    @Override
    public Boolean addPlanDayVO(PlanDayVO planDayVO) {
        return this.baseMapper.insert(planDayVO.toPlanDay())> 0;
    }

    @Override
    public PlanDay updatePlanDayVO(PlanDayVO planDayVO) {
        this.baseMapper.updateById(planDayVO.toPlanDay());
        PlanDay planDay = this.baseMapper.selectById(planDayVO.getId());
        return planDay;
    }

    @Override
    public PlanDay updatePlanDay(PlanDay planDay) {
        this.baseMapper.updateById(planDay);
        return this.baseMapper.selectById(planDay.getId());
    }

    @Override
    public Integer getPlanDayForMakeNum(Long planId, Long planDayId) {
        EntityWrapper<PlanDay> wrapper = new EntityWrapper<>();
        wrapper.eq("plan_id", planId);
        if(planDayId != null){
            wrapper.eq("id", planDayId);
        }
        Integer result = this.baseMapper.getPlanDayForMakeNum(wrapper);
        return result;
    }

    @Override
    public Integer getPlanForMakeNum(Long planId, Long jobId) {
        EntityWrapper<PlanDay> wrapper = new EntityWrapper<>();
        wrapper.eq("plan_id", planId);
        if(jobId != null){
            wrapper.eq("job_id", jobId);
        }
        Integer result = this.baseMapper.getPlanDayForMakeNum(wrapper);
        return result;
    }

    @Override
    public Page<PlanDayVO> getPagePlanDayVO(Page<PlanDayVO> page, EntityWrapper<PlanDayVO> wrapper) {
        return page.setRecords(this.baseMapper.getPagePlanDayVO(page, wrapper));
    }
}
