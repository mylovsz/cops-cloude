package com.cops.scada.service.impl;

import com.cops.scada.entity.ManHour;
import com.cops.scada.dao.ManHourDao;
import com.cops.scada.service.ManHourService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 工时管理 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ManHourServiceImpl extends ServiceImpl<ManHourDao, ManHour> implements ManHourService {

}
