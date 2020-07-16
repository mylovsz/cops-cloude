package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.ExaminesA;
import com.cops.scada.dao.ExaminesADao;
import com.cops.scada.entity.ExaminesB;
import com.cops.scada.entity.VO.ExaminesAVO;
import com.cops.scada.entity.VO.ExaminesTestTime;
import com.cops.scada.entity.VO.QualityExaminesVO;
import com.cops.scada.entity.VO.StatisticsExaminesCollect;
import com.cops.scada.service.ExaminesAService;
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
 * @since 2019-03-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExaminesAServiceImpl extends ServiceImpl<ExaminesADao, ExaminesA> implements ExaminesAService {

    @Override
    public ExaminesA getExaminesAByProduceId(Long produceId, Date collectDate) {
        EntityWrapper<ExaminesA> wrapper = new EntityWrapper<>();
        wrapper.eq("produce_id", produceId);
        Date start = DateUtil.getStartTime(collectDate);
        Date end = DateUtil.getEndTime(collectDate);
        wrapper.between("collect_date", start, end);
        return this.baseMapper.getExaminesAOne(wrapper);
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
    public List<ExaminesAVO> getAllExaminesAVO(String planId) {
        EntityWrapper<ExaminesAVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan.id", planId);
        return this.baseMapper.getAllExaminesAVO(wrapper);
    }

    @Override
    public Page<ExaminesAVO> getExaminesAVO(Page<ExaminesAVO> page, EntityWrapper<ExaminesAVO> wrapper) {
        return page.setRecords(this.baseMapper.getExaminesAVO(page, wrapper));
    }

    @Override
    public Long deleteDup() {
        return this.baseMapper.deleteDup();
    }

    @Override
    public List<ExaminesAVO> getAllExaminesAVOToExcal(EntityWrapper<ExaminesAVO> wrapper){
        return this.baseMapper.getAllExaminesAVOToExcal(wrapper);
    }
}
