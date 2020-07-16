package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.Repair;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cops.scada.entity.VO.RepairVO;
import com.cops.scada.entity.VO.StatisticsRepairVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 维修管理 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2019-04-17
 */
public interface RepairDao extends BaseMapper<Repair> {
    List<RepairVO> getRepairVO(Page<RepairVO> page, @Param("ew") EntityWrapper<RepairVO> wrapper);

    List<RepairVO> getRepairVODetail(@Param("ew") EntityWrapper<RepairVO> wrapper);

    Map getRepairReportResponsibleDepartmentVO(@Param("t1") Date t1 , @Param("t2") Date t2);

    Integer getRepairForCount(@Param("ew") EntityWrapper<RepairVO> wrapper);

    List<StatisticsRepairVO> getStatisticsRepairVO(@Param("obj") String obj , @Param("ew") EntityWrapper<RepairVO> wrapper);
}
