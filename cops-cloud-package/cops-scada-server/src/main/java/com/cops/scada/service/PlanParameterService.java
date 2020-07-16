package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.PlanParameter;
import com.baomidou.mybatisplus.service.IService;
import com.cops.scada.entity.VO.PlanParameterVO;
import com.cops.scada.entity.VO.PlanProductVO;

/**
 * <p>
 * 工单统计参数 服务类
 * </p>
 *
 * @author wanglm
 * @since 2019-03-21
 */
public interface PlanParameterService extends IService<PlanParameter> {

    /**
     * 根据计划ID和label名称查询内容
     * @param planId
     * @param label
     * @return
     */
    PlanParameter getPlanParameter(Long planId, Integer label);

    /**
     * 根据计划ID，查询点火电压上线
     * @param planId
     * @return
     */
    Double getHotVU(Long planId);

    /**
     * 根据计划ID，查询点火电压下线
     * @param planId
     * @return
     */
    Double getHotVL(Long planId);

    /**
     * 根据计划ID，查询点火时长上线
     * @param planId
     * @return
     */
    Double getHotTU(Long planId);

    /**
     * 根据计划ID，查询点火时长下线
     * @param planId
     * @return
     */
    Double getHotTL(Long planId);

    Page<PlanParameterVO> getPlanParameterVO(Page<PlanParameterVO> page, EntityWrapper<PlanParameterVO> wrapper);
}
