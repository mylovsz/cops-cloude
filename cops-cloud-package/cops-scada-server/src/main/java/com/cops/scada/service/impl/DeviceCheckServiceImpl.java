package com.cops.scada.service.impl;

import com.cops.scada.entity.DeviceCheck;
import com.cops.scada.dao.DeviceCheckDao;
import com.cops.scada.service.DeviceCheckService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 设备校检 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceCheckServiceImpl extends ServiceImpl<DeviceCheckDao, DeviceCheck> implements DeviceCheckService {

}
