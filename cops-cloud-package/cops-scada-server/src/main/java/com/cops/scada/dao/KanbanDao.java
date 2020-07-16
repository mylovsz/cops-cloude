package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.Kanban;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cops.scada.entity.VO.PlanDayVO;
import com.cops.scada.entity.VO.WorkDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 看板配置 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2020-04-09
 */
public interface KanbanDao extends BaseMapper<Kanban> {

    /**
     * 分页查询-获取今日排产计划工单数据
     * @param page
     * @param wrapper
     * @return
     */
    List<PlanDayVO> getPagePlanDayVO(Page<PlanDayVO> page, @Param("ew") EntityWrapper<PlanDayVO> wrapper);

    /**
     *获取今日排产计划工时数据
     * @param wrapper
     * @return
     */
    List<WorkDetailVO> getWorkDetailVO(@Param("ew") EntityWrapper<WorkDetailVO> wrapper);
}
