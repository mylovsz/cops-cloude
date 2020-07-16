package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.VO.WorkDetailVO;
import com.cops.scada.entity.VO.statistic.TotalWorkDetailVO;
import com.cops.scada.entity.WorkDetail;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 工时详情 服务类
 * </p>
 *
 * @author wanglm
 * @since 2020-04-08
 */
public interface WorkDetailService extends IService<WorkDetail> {

    /**
     * 通用分页查询
     * @param page
     * @param wrapper
     * @return
     */
    Page<WorkDetailVO> getWorkDetailVO(Page<WorkDetailVO> page, EntityWrapper<WorkDetailVO> wrapper);

    /**
     * 根据ID获取实际的实体
     * @param id
     * @return
     */
    WorkDetailVO getWorkDetailVO(Long id);

    /**
     * 获取指定 plan day 的统计工时
     * @param planDayId
     * @return
     */
    TotalWorkDetailVO getTotalWorkDetailVO(Long planDayId);

    /**
     * 获取指定 plan day 正在加工的
     * @param planDayId
     * @return
     */
    List<WorkDetailVO> listForGoingByPlanDay(Long planDayId);
}
