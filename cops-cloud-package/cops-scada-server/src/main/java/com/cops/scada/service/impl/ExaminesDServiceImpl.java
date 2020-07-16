package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.ExaminesD;
import com.cops.scada.dao.ExaminesDDao;
import com.cops.scada.entity.VO.*;
import com.cops.scada.service.ExaminesDService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cops.scada.util.DateUtil;
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
public class ExaminesDServiceImpl extends ServiceImpl<ExaminesDDao, ExaminesD> implements ExaminesDService {

    @Override
    public ExaminesD getExaminesDByProduceId(Long produceId, Date collectDate) {
        EntityWrapper<ExaminesD> wrapper = new EntityWrapper<>();
        wrapper.eq("produce_id", produceId);
        Date start = DateUtil.getStartTime(collectDate);
        Date end = DateUtil.getEndTime(collectDate);
        wrapper.between("collect_date", start, end);
        return this.baseMapper.getExaminesDOne(wrapper);
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
    public Page<QualityExaminesVO> getQulityDay1(Page<QualityExaminesVO> page, Date start,Date end){
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
    public List<ExaminesDVO> getAllExaminesDVO(String planId) {
        EntityWrapper<ExaminesDVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan.id", planId);
        return this.baseMapper.getAllExaminesDVO(wrapper);
    }

    @Override
    public Page<ExaminesDVO> getExaminesDVO(Page<ExaminesDVO> page, EntityWrapper<ExaminesDVO> wrapper) {
        return page.setRecords(this.baseMapper.getExaminesDVO(page, wrapper));
    }

    @Override
    public Long deleteDup() {
        return this.baseMapper.deleteDup();
    }

    @Override
    public List<ExaminesDVO> getAllExaminesDVOToExcal(EntityWrapper<ExaminesDVO> wrapper){
        return this.baseMapper.getAllExaminesDVOToExcal(wrapper);
    }
}
