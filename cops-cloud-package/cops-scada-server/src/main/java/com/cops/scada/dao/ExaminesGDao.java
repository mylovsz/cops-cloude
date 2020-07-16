package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.scada.entity.ExaminesG;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cops.scada.entity.VO.ExaminesTestTime;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 初检耐压 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2019-03-20
 */
public interface ExaminesGDao extends BaseMapper<ExaminesG> {

    /**
     * 获取测试时间
     * @param wrapper
     * @return
     */
    List<ExaminesTestTime> getExaminesTestTime(@Param("ew") EntityWrapper<ExaminesTestTime> wrapper);
}
