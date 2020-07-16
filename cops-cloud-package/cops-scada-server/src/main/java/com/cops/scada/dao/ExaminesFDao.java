package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.ExaminesF;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cops.scada.entity.VO.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 初检耐压 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2019-03-20
 */
public interface ExaminesFDao extends BaseMapper<ExaminesF> {

    /**
     * 获取日统计质量数据
     * @param collectDate
     * @return
     */
    List<QualityExaminesVO> getQulityDay(Page<QualityExaminesVO> page, @Param("collectDate") Date collectDate);
    List<QualityExaminesVO> getQulityDay1(Page<QualityExaminesVO> page,@Param("start")Date start,@Param("end")Date end);

    /**
     * 获取日统计质量数据
     * @param start
     * @param end
     * @return
     */
    List<QualityExaminesVO> getQulityDayKanban(@Param("start")Date start,@Param("end")Date end);

    List<QualityExaminesVO> getQulityDay3(Page<QualityExaminesVO> page, @Param("start")Date start,@Param("end")Date end,@Param("ew") EntityWrapper<QualityExaminesVO> wrapper);

    List<StatisticsExaminesCollect> getStatisticsExaminesCollect(@Param("ew") EntityWrapper<StatisticsExaminesCollect> wrapper);

    /**
     * 删除重复数据
     * @return
     */
    Long deleteDup();

    /**
     * 获取所有数据
     * @param wrapper
     * @return
     */
    List<ExaminesFVO> getAllExaminesFVO(@Param("ew") EntityWrapper<ExaminesFVO> wrapper);

    /**
     * 获取分页信息
     * @param page
     * @param wrapper
     * @return
     */
    List<ExaminesFVO> getExaminesFVO(Page<ExaminesFVO> page, @Param("ew") EntityWrapper<ExaminesFVO> wrapper);

    /**
     * 获取测试时间
     * @param wrapper
     * @return
     */
    List<ExaminesTestTime> getExaminesTestTime(@Param("ew")EntityWrapper<ExaminesTestTime> wrapper);


    List<ExaminesFVO> getAllExaminesFVOToExcal( @Param("ew") EntityWrapper<ExaminesFVO> wrapper);

    ExaminesF getExaminesFOne(@Param("ew") EntityWrapper<ExaminesF> wrapper);
}
