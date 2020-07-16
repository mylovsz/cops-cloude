package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.PlanDay;
import com.baomidou.mybatisplus.service.IService;
import com.cops.scada.entity.VO.PlanDayVO;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 日计划统计 服务类
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
public interface PlanDayService extends IService<PlanDay> {

    /**
     * 根据工单ID，Job类型，获取该天的数据记录
     * @param date
     * @param planId
     * @param jobSn
     * @return
     */
    List<PlanDayVO> getPlanDayVOForDay(Date date, Long planId, String jobSn);

    PlanDayVO getPlanDay(Long id);

    Boolean addPlanDay(PlanDay planDay);

    Boolean addPlanDayVO(PlanDayVO planDayVO);

    PlanDay updatePlanDayVO(PlanDayVO planDayVO);

    PlanDay updatePlanDay(PlanDay planDay);

    /**
     * 获取工单已经报工的数量
     * @param planId 要统计的工单
     * @param planDayId 要排产的工单，如果为null，则表示全部
     * @return
     */
    Integer getPlanDayForMakeNum(Long planId, Long planDayId);

    /**
     * 获取工单已经报工的数量
     * @param planId 要统计的工单
     * @param jobId 要排产的工序，如果为null，则表示全部
     * @return
     */
    Integer getPlanForMakeNum(Long planId, Long jobId);

    /**
     * 通用分页查询
     * @param page
     * @param wrapper
     * @return
     */
    Page<PlanDayVO> getPagePlanDayVO(Page<PlanDayVO> page, EntityWrapper<PlanDayVO> wrapper);
}
