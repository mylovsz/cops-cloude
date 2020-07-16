package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.ExaminesE;
import com.cops.scada.dao.ExaminesEDao;
import com.cops.scada.entity.VO.ExaminesEVO;
import com.cops.scada.entity.VO.ExaminesTestTime;
import com.cops.scada.entity.VO.QualityExaminesVO;
import com.cops.scada.entity.VO.StatisticsExaminesCollect;
import com.cops.scada.service.ExaminesEService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 点火数据 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-03-20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExaminesEServiceImpl extends ServiceImpl<ExaminesEDao, ExaminesE> implements ExaminesEService {

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
    public List<ExaminesEVO> getAllExaminesEVO(String planId) {
        EntityWrapper<ExaminesEVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan.id", planId);
        return this.baseMapper.getAllExaminesEVO(wrapper);
    }

    @Override
    public Page<ExaminesEVO> getExaminesEVO(Page<ExaminesEVO> page, EntityWrapper<ExaminesEVO> wrapper) {
        return page.setRecords(this.baseMapper.getExaminesEVO(page, wrapper));
    }

    @Override
    public Long deleteDup() {
        return this.baseMapper.deleteDup();
    }

    @Override
    public List<ExaminesEVO> getAllExaminesEVOToExcal(EntityWrapper<ExaminesEVO> wrapper){
        return this.baseMapper.getAllExaminesEVOToExcal(wrapper);
    }
}
