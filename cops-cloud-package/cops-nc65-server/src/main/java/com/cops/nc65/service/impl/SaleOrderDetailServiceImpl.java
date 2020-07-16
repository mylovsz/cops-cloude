package com.cops.nc65.service.impl;


import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cops.entity.nc65.SaleOrder;
import com.cops.nc65.dao.SaleOrderDetailDao;
import com.cops.entity.nc65.SaleOrderDetail;
import com.cops.nc65.service.SaleOrderDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SaleOrderDetailServiceImpl extends ServiceImpl<SaleOrderDetailDao, SaleOrderDetail> implements SaleOrderDetailService {
    @Override
    public List<SaleOrderDetail> getSaleOrderDetailList() {
        return this.baseMapper.getSaleOrderDetail();
    }

    @Override
    public List<SaleOrderDetail> getSaleOrderDetails(Wrapper wrapper) {
        return this.baseMapper.getSaleOrderDetails(wrapper);
    }

}
