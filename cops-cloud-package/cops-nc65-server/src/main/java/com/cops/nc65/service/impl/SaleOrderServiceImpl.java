package com.cops.nc65.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cops.nc65.dao.SaleOrderDao;
import com.cops.entity.nc65.SaleOrder;
import com.cops.nc65.service.SaleOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderDao, SaleOrder> implements SaleOrderService {
    @Override
    public List<SaleOrder> getSaleOrderAll() {
        return this.baseMapper.getAll();
    }

    @Override
    public Page<SaleOrder> getPage(Page<SaleOrder> page, Wrapper wrapper) {
        return page.setRecords(this.baseMapper.getPage(page, wrapper));
    }
}
