package com.cops.scada.service.impl;

import com.cops.scada.entity.ExaminesCExperience;
import com.cops.scada.dao.ExaminesCExperienceDao;
import com.cops.scada.service.ExaminesCExperienceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 老化数据内容 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2020-01-03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExaminesCExperienceServiceImpl extends ServiceImpl<ExaminesCExperienceDao, ExaminesCExperience> implements ExaminesCExperienceService {

}
