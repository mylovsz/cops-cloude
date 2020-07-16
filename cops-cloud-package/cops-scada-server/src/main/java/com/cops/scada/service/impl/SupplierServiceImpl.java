package com.cops.scada.service.impl;

import com.cops.scada.entity.Supplier;
import com.cops.scada.dao.SupplierDao;
import com.cops.scada.service.SupplierService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 设备供应商 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SupplierServiceImpl extends ServiceImpl<SupplierDao, Supplier> implements SupplierService {

}
