package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.ExaminesC;
import com.cops.scada.dao.ExaminesCDao;
import com.cops.scada.entity.VO.*;
import com.cops.scada.service.ExaminesCService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class ExaminesCServiceImpl extends ServiceImpl<ExaminesCDao, ExaminesC> implements ExaminesCService {

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
    public List<ExaminesCVO> getAllExaminesCVO(String planId) {
        EntityWrapper<ExaminesCVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan.id", planId);
        return this.baseMapper.getAllExaminesCVO(wrapper);
    }

    @Override
    public Page<ExaminesCVO> getExaminesCVO(Page<ExaminesCVO> page, EntityWrapper<ExaminesCVO> wrapper) {
        return page.setRecords(this.baseMapper.getExaminesCVO(page, wrapper));
    }

    @Override
    public Long deleteDup() {
        return this.baseMapper.deleteDup();
    }

    @Override
    public List<ExaminesCVO> getAllExaminesCVOToExcal(EntityWrapper<ExaminesCVO> wrapper){
        return this.baseMapper.getAllExaminesCVOToExcal(wrapper);
    }
}
