package com.cops.scada.service.impl;

import com.cops.scada.entity.DeviceRepair;
import com.cops.scada.dao.DeviceRepairDao;
import com.cops.scada.service.DeviceRepairService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 设备维修 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceRepairServiceImpl extends ServiceImpl<DeviceRepairDao, DeviceRepair> implements DeviceRepairService {

}
