package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.VO.WorkDetailVO;
import com.cops.scada.entity.VO.statistic.TotalWorkDetailVO;
import com.cops.scada.entity.WorkDetail;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 工时详情 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2020-04-08
 */
public interface WorkDetailDao extends BaseMapper<WorkDetail> {

    /**
     * 获取工时信息
     * @param page
     * @param wrapper
     * @return
     */
    List<WorkDetailVO> getWorkDetailDao(Page<WorkDetailVO> page, @Param("ew") EntityWrapper<WorkDetailVO> wrapper);

    /**
     * 获取实际的实体
     * @param wrapper
     * @return
     */
    List<WorkDetailVO> getWorkDetailDao(@Param("ew") EntityWrapper<WorkDetailVO> wrapper);

    /**
     * 获取统计信息
     * @param wrapper
     * @return
     */
    TotalWorkDetailVO getTotalWorkDetailVO(@Param("ew") EntityWrapper<WorkDetailVO> wrapper);
}
