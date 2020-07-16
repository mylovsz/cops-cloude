package com.cops.scada.service.impl;

import com.cops.scada.entity.Device;
import com.cops.scada.dao.DeviceDao;
import com.cops.scada.service.DeviceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 设备管理 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceServiceImpl extends ServiceImpl<DeviceDao, Device> implements DeviceService {

}
