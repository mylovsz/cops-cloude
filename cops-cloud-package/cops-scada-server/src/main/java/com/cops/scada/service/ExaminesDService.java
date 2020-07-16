package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.ExaminesD;
import com.baomidou.mybatisplus.service.IService;
import com.cops.scada.entity.VO.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 初检耐压 服务类
 * </p>
 *
 * @author wanglm
 * @since 2019-03-20
 */
public interface ExaminesDService extends IService<ExaminesD> {

    ExaminesD getExaminesDByProduceId(Long produceId, Date collectDate);

    /**
     * 获取测试时间
     * @param wrapper
     * @return
     */
    List<ExaminesTestTime> getExaminesTestTime(EntityWrapper<ExaminesTestTime> wrapper);

    /**
     * 获取日统计质量数据
     * @param collectDate
     * @return
     */
    Page<QualityExaminesVO> getQulityDay(Page<QualityExaminesVO> page, Date collectDate);
    Page<QualityExaminesVO> getQulityDay1(Page<QualityExaminesVO> page, Date start,Date end);

    /**
     * 根据日期选择
     * @param start
     * @param end
     * @return
     */
    List<QualityExaminesVO> getQulityDayKanban(Date start,Date end);

    Page<QualityExaminesVO> getQulityDay3(Page<QualityExaminesVO> page, Date start,Date end,EntityWrapper<QualityExaminesVO> wrapper);
    /**
     * 根据日期查询对应的统计数据
     * @param start
     * @param end
     * @return
     */
    List<StatisticsExaminesCollect> getStatisticsExaminesCollect(Date start, Date end);

    /**
     * 获取信息
     * @param wrapper
     * @return
     */
    List<ExaminesDVO> getAllExaminesDVO(String planId);

    /**
     * 获取分页信息
     * @param page
     * @param wrapper
     * @return
     */
    Page<ExaminesDVO> getExaminesDVO(Page<ExaminesDVO> page, EntityWrapper<ExaminesDVO> wrapper);

    /**
     * 删除重复项
     * @return
     */
    Long deleteDup();

    List<ExaminesDVO> getAllExaminesDVOToExcal(EntityWrapper<ExaminesDVO> wrapper);
}
