package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.Repair;
import com.cops.scada.dao.RepairDao;
import com.cops.scada.entity.VO.RepairVO;
import com.cops.scada.entity.VO.StatisticsRepairVO;
import com.cops.scada.service.RepairService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cops.scada.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 维修管理 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-04-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RepairServiceImpl extends ServiceImpl<RepairDao, Repair> implements RepairService {

    @Override
    public Page<RepairVO> getPageRepairVO(Page<RepairVO> page, EntityWrapper<RepairVO> wrapper) {
        return page.setRecords(this.baseMapper.getRepairVO(page, wrapper));
    }

    @Override
    public List<RepairVO> getPageRepairVO(EntityWrapper<RepairVO> wrapper) {
        return this.baseMapper.getRepairVODetail(wrapper);
    }

    @Override
    public Repair getOneByProduceId(Long id, Integer state) {
        EntityWrapper<Repair> wrapper = new EntityWrapper<>();
        wrapper.eq("produce_id", id);
        wrapper.eq("state", state);
        return selectOne(wrapper);
    }

    @Override
    public List<StatisticsRepairVO> getStatisticsRepairVOForFaultCode(EntityWrapper<RepairVO> wrapper) {
        return this.baseMapper.getStatisticsRepairVO("dict.label", wrapper);
    }

    @Override
    public List<StatisticsRepairVO> getStatisticsRepairVOForResponsible(EntityWrapper<RepairVO> wrapper) {
        return this.baseMapper.getStatisticsRepairVO("dict1.label", wrapper);
    }

    @Override
    public List<StatisticsRepairVO> getStatisticsRepairVOForState(EntityWrapper<RepairVO> wrapper) {
        return this.baseMapper.getStatisticsRepairVO("dict2.label", wrapper);
    }

    @Override
    public Integer getRepairForCountBy(Long planId, Date repairDate, String factorySiteSn) {
        EntityWrapper<RepairVO> wrapper = new EntityWrapper<>();

        if(planId != null)
            wrapper.eq("plan.id", planId);

        if(repairDate != null)
            wrapper.between("repair_m.fault_date", DateUtil.getStartTime(repairDate), DateUtil.getEndTime(repairDate));

        if(factorySiteSn != null)
            wrapper.like("factory_site.sn", factorySiteSn);

        return this.baseMapper.getRepairForCount(wrapper);
    }


    public Map getRepairReportResponsibleDepartmentVO(Date t1 ,Date t2 ) {
        return this.baseMapper.getRepairReportResponsibleDepartmentVO(t1,t2);
    }

}
