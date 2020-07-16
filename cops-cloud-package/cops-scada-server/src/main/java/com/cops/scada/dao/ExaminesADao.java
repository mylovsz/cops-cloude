package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.ExaminesA;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cops.scada.entity.ExaminesB;
import com.cops.scada.entity.VO.ExaminesAVO;
import com.cops.scada.entity.VO.ExaminesTestTime;
import com.cops.scada.entity.VO.QualityExaminesVO;
import com.cops.scada.entity.VO.StatisticsExaminesCollect;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 初检耐压 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2019-03-22
 */
public interface ExaminesADao extends BaseMapper<ExaminesA> {

    ExaminesA getExaminesAOne(@Param("ew") EntityWrapper<ExaminesA> wrapper);

    /**
     * 获取日统计质量数据
     * @param collectDate
     * @return
     */
    List<QualityExaminesVO> getQulityDay(Page<QualityExaminesVO> page, @Param("collectDate")Date collectDate);
    /**
     * 获取日统计质量数据
     * @param collectDate
     * @return
     */
    List<QualityExaminesVO> getQulityDay1(Page<QualityExaminesVO> page, @Param("start")Date start,@Param("end")Date end);


    /**
     * 获取日统计质量数据
     * @param start
     * @param end
     * @return
     */
    List<QualityExaminesVO> getQulityDayKanban(@Param("start")Date start,@Param("end")Date end);

    List<QualityExaminesVO> getQulityDay3(Page<QualityExaminesVO> page, @Param("start")Date start,@Param("end")Date end,@Param("ew") EntityWrapper<QualityExaminesVO> wrapper);
    /**
     * 获取统计信息
     * @param wrapper
     * @return
     */
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
    List<ExaminesAVO> getAllExaminesAVO(@Param("ew") EntityWrapper<ExaminesAVO> wrapper);

    /**
     * 获取分页信息
     * @param page
     * @param wrapper
     * @return
     */
    List<ExaminesAVO> getExaminesAVO(Page<ExaminesAVO> page, @Param("ew") EntityWrapper<ExaminesAVO> wrapper);

    /**
     * 获取测试时间
     * @param wrapper
     * @return
     */
    List<ExaminesTestTime> getExaminesTestTime(@Param("ew")EntityWrapper<ExaminesTestTime> wrapper);

    List<ExaminesAVO> getAllExaminesAVOToExcal( @Param("ew") EntityWrapper<ExaminesAVO> wrapper);
}
