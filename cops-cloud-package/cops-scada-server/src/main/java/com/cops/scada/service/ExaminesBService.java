package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.ExaminesB;
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
public interface ExaminesBService extends IService<ExaminesB> {

    ExaminesB getExaminesBByProduceId(Long produceId, Date collectDate);

    Long addExaminesBToCache(Long produceId, Long timeout);
    boolean hasExaminesBForCache(Long produceId);

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
    /**
     * 获取日统计质量数据
     * @param collectDate
     * @return
     */
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
    List<ExaminesBVO> getAllExaminesBVO(String planId);

    /**
     * 获取分页信息
     * @param page
     * @param wrapper
     * @return
     */
    Page<ExaminesBVO> getExaminesBVO(Page<ExaminesBVO> page, EntityWrapper<ExaminesBVO> wrapper);

    /**
     * 删除重复项
     * @return
     */
    Long deleteDup();

    List<ExaminesBVO> getAllExaminesBVOToExcal(EntityWrapper<ExaminesBVO> wrapper);
}
