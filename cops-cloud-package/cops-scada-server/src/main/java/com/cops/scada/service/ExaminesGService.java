package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.scada.entity.ExaminesG;
import com.baomidou.mybatisplus.service.IService;
import com.cops.scada.entity.VO.ExaminesTestTime;

import java.util.List;

/**
 * <p>
 * 初检耐压 服务类
 * </p>
 *
 * @author wanglm
 * @since 2019-03-20
 */
public interface ExaminesGService extends IService<ExaminesG> {

    /**
     * 获取测试时间
     * @param wrapper
     * @return
     */
    List<ExaminesTestTime> getExaminesTestTime(EntityWrapper<ExaminesTestTime> wrapper);

}
