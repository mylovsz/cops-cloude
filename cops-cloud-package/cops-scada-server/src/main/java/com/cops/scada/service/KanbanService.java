package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.Kanban;
import com.baomidou.mybatisplus.service.IService;
import com.cops.scada.entity.VO.PlanDayVO;
import com.cops.scada.entity.VO.WorkDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 看板配置 服务类
 * </p>
 *
 * @author wanglm
 * @since 2020-04-09
 */
public interface KanbanService extends IService<Kanban> {

    /**
     * 获取今日排产计划看板_工单数据
     * @param page
     * @param wrapper
     * @return
     */
    Page<PlanDayVO> getPagePlanDayVO(Page<PlanDayVO> page, EntityWrapper<PlanDayVO> wrapper);

    List<WorkDetailVO> getWorkDetailVO(@Param("ew") EntityWrapper<WorkDetailVO> wrapper);


}
