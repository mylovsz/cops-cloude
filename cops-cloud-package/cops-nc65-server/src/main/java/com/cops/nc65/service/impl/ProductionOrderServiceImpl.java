package com.cops.nc65.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cops.entity.nc65.ProductionOrder;
import com.cops.nc65.dao.ProductionOrderDao;
import com.cops.nc65.service.ProductionOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductionOrderServiceImpl extends ServiceImpl<ProductionOrderDao, ProductionOrder> implements ProductionOrderService {
    @Override
    public List<ProductionOrder> getProductionOrders(Wrapper wrapper) {
        return this.baseMapper.getProductionOrders(wrapper);
    }

    @Override
    public Page<ProductionOrder> getPage(Page<ProductionOrder> page, Wrapper wrapper) {
        return page.setRecords(this.baseMapper.getPage(page, wrapper));
    }
}
