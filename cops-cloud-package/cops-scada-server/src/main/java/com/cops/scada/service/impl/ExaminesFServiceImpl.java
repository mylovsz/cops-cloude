package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.ExaminesF;
import com.cops.scada.dao.ExaminesFDao;
import com.cops.scada.entity.VO.*;
import com.cops.scada.service.ExaminesFService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cops.scada.service.MemaryCacheService;
import com.cops.scada.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 初检耐压 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-03-20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExaminesFServiceImpl extends ServiceImpl<ExaminesFDao, ExaminesF> implements ExaminesFService {

    @Autowired
    private MemaryCacheService memaryCacheService;

    @Override
    public ExaminesF getExaminesFByProduceId(Long produceId, Date collectDate) {
        EntityWrapper<ExaminesF> wrapper = new EntityWrapper<>();
        wrapper.eq("produce_id", produceId);
        Date start = DateUtil.getStartTime(collectDate);
        Date end = DateUtil.getEndTime(collectDate);
        wrapper.between("collect_date", start, end);
        return this.baseMapper.getExaminesFOne(wrapper);
    }

    @Override
    public Long addExaminesFToCache(Long produceId, Long timeout) {
        return memaryCacheService.sSetAndTime("PlanDayExaminesF", timeout, produceId);
    }

    @Override
    public boolean hasExaminesFForCache(Long produceId) {
        return memaryCacheService.sHasKey("PlanDayExaminesF", produceId);
    }

    @Override
    public List<ExaminesTestTime> getExaminesTestTime(EntityWrapper<ExaminesTestTime> wrapper) {
        return this.baseMapper.getExaminesTestTime(wrapper);
    }

    @Override
    public Page<QualityExaminesVO> getQulityDay(Page<QualityExaminesVO> page, Date collectDate){
        return page.setRecords(this.baseMapper.getQulityDay(page, collectDate));
    }
    @Override
    public Page<QualityExaminesVO> getQulityDay1(Page<QualityExaminesVO> page,  Date start,Date end){
        return page.setRecords(this.baseMapper.getQulityDay1(page, start,end));
    }

    @Override
    public List<QualityExaminesVO> getQulityDayKanban(Date start, Date end) {
        return this.baseMapper.getQulityDayKanban(start, end);
    }

    @Override
    public Page<QualityExaminesVO> getQulityDay3(Page<QualityExaminesVO> page, Date start,Date end,EntityWrapper<QualityExaminesVO> wrapper){
        return page.setRecords(this.baseMapper.getQulityDay3(page, start,end,wrapper));
    }


    @Override
    public List<StatisticsExaminesCollect> getStatisticsExaminesCollect(Date start, Date end) {

        EntityWrapper<StatisticsExaminesCollect> wrapper = new EntityWrapper<>();

        if (start != null) {
            wrapper.ge("collect_date", com.cops.scada.util.DateUtil.getStartTime(start));
            if (end != null) {
                wrapper.le("collect_date", com.cops.scada.util.DateUtil.getEndTime(end));
            }
        }
        return this.baseMapper.getStatisticsExaminesCollect(wrapper);
    }

    @Override
    public List<ExaminesFVO> getAllExaminesFVO(String planId) {
        EntityWrapper<ExaminesFVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan.id", planId);
        return this.baseMapper.getAllExaminesFVO(wrapper);
    }

    @Override
    public Page<ExaminesFVO> getExaminesFVO(Page<ExaminesFVO> page, EntityWrapper<ExaminesFVO> wrapper) {
        return page.setRecords(this.baseMapper.getExaminesFVO(page, wrapper));
    }

    @Override
    public Long deleteDup() {
        return this.baseMapper.deleteDup();
    }

    @Override
    public List<ExaminesFVO> getAllExaminesFVOToExcal(EntityWrapper<ExaminesFVO> wrapper){
        return this.baseMapper.getAllExaminesFVOToExcal(wrapper);
    }
}
