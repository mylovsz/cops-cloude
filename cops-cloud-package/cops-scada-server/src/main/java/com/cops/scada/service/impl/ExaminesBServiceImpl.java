package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.ExaminesB;
import com.cops.scada.dao.ExaminesBDao;
import com.cops.scada.entity.VO.*;
import com.cops.scada.service.ExaminesBService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class ExaminesBServiceImpl extends ServiceImpl<ExaminesBDao, ExaminesB> implements ExaminesBService {

    @Autowired
    MemaryCacheServiceImpl memaryCacheService;

    @Override
    public ExaminesB getExaminesBByProduceId(Long produceId, Date collectDate) {
        EntityWrapper<ExaminesB> wrapper = new EntityWrapper<>();
        wrapper.eq("produce_id", produceId);
        Date start = DateUtil.getStartTime(collectDate);
        Date end = DateUtil.getEndTime(collectDate);
        wrapper.between("collect_date", start, end);
        return this.baseMapper.getExaminesBOne(wrapper);
    }

    @Override
    public Long addExaminesBToCache(Long produceId, Long timeout) {
        return memaryCacheService.sSet("PlanDayExaminesB", timeout, produceId);
    }

    @Override
    public boolean hasExaminesBForCache(Long produceId) {
        return memaryCacheService.sHasKey("PlanDayExaminesB", produceId);
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
    public List<QualityExaminesVO> getQulityDayKanban(Date start, Date end) {
        return this.baseMapper.getQulityDayKanban(start, end);
    }

    @Override
    public Page<QualityExaminesVO> getQulityDay1(Page<QualityExaminesVO> page,  Date start,Date end){
        return page.setRecords(this.baseMapper.getQulityDay1(page, start,end));
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
    public List<ExaminesBVO> getAllExaminesBVO(String planId) {
        EntityWrapper<ExaminesBVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan.id", planId);
        return this.baseMapper.getAllExaminesBVO(wrapper);
    }

    @Override
    public Page<ExaminesBVO> getExaminesBVO(Page<ExaminesBVO> page, EntityWrapper<ExaminesBVO> wrapper) {
        return page.setRecords(this.baseMapper.getExaminesBVO(page, wrapper));
    }

    @Override
    public Long deleteDup() {
        return this.baseMapper.deleteDup();
    }

    @Override
    public List<ExaminesBVO> getAllExaminesBVOToExcal(EntityWrapper<ExaminesBVO> wrapper){
        return this.baseMapper.getAllExaminesBVOToExcal(wrapper);
    }
}
