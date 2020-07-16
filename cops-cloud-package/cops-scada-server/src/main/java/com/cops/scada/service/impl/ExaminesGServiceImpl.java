package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.scada.entity.ExaminesG;
import com.cops.scada.dao.ExaminesGDao;
import com.cops.scada.entity.VO.ExaminesTestTime;
import com.cops.scada.service.ExaminesGService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ExaminesGServiceImpl extends ServiceImpl<ExaminesGDao, ExaminesG> implements ExaminesGService {

    @Override
    public List<ExaminesTestTime> getExaminesTestTime(EntityWrapper<ExaminesTestTime> wrapper) {
        return this.baseMapper.getExaminesTestTime(wrapper);
    }

}
