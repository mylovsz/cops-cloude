package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.Plan;
import com.baomidou.mybatisplus.service.IService;
import com.cops.scada.entity.VO.PlanProductVO;
import com.cops.scada.entity.VO.PlanQualityDetailVO;
import com.cops.scada.entity.VO.StatisticsPlanVO;
import org.apache.ibatis.annotations.Param;

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
public interface PlanService extends IService<Plan> {

    PlanQualityDetailVO getPlanQualityDetailVO(Long planId);

    Boolean existsBySN(String sn);

    Plan getPlanByNcSn(String ncSn);

    Boolean existsBySN(String sn, Long id);

    Boolean addPlan(Plan plan);

    Integer going(Plan plan);

    /**
     * 根据产品编号获取计划
     * @param productId
     * @return
     */
    Plan getPlanByProduct(Long productId);

    /**
     * 根据订单获取计划
     * @param sn
     * @return
     */
    Plan getPlanBySN(String sn);


    /**
     * 获取所有的plan
     * @return
     */
    List<Plan> getAllPlan();

    /**
     * 获取所有未完成的plan
     * @return
     */
    List<Plan> getPlanForUndone();

    /**
     * 返回工单的统计信息
     * @return
     */
    StatisticsPlanVO getStatisticsPlanVO();

    /**
     * 返回工单的统计信息
     * @return
     */
    StatisticsPlanVO getStatisticsPlanVO(Integer productType, Date startDate, Date endDate);

    /**
     * 返回Hid工单的统计信息
     * @return
     */
    StatisticsPlanVO getStatisticsHidPlanVO();

    /**
     * 返回Hid工单的统计信息
     * @return
     */
    StatisticsPlanVO getStatisticsLedPlanVO();

    /**
     * 通用分页查询
     * @param page
     * @param wrapper
     * @return
     */
    Page<PlanProductVO> getPlanProduct(Page<PlanProductVO> page, EntityWrapper<PlanProductVO> wrapper);


}
