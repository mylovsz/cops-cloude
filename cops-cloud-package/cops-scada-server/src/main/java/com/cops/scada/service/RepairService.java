package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.Repair;
import com.baomidou.mybatisplus.service.IService;
import com.cops.scada.entity.VO.RepairVO;
import com.cops.scada.entity.VO.StatisticsRepairVO;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 维修管理 服务类
 * </p>
 *
 * @author wanglm
 * @since 2019-04-17
 */
public interface RepairService extends IService<Repair> {

    Page<RepairVO> getPageRepairVO(Page<RepairVO> page, EntityWrapper<RepairVO> wrapper);

    List<RepairVO> getPageRepairVO(EntityWrapper<RepairVO> wrapper);

    Repair getOneByProduceId(Long id, Integer state);

    List<StatisticsRepairVO> getStatisticsRepairVOForFaultCode(EntityWrapper<RepairVO> wrapper);

    List<StatisticsRepairVO> getStatisticsRepairVOForResponsible(EntityWrapper<RepairVO> wrapper);

    List<StatisticsRepairVO> getStatisticsRepairVOForState(EntityWrapper<RepairVO> wrapper);

    Integer getRepairForCountBy(Long planId, Date repairDate, String factorySiteSn);
}
