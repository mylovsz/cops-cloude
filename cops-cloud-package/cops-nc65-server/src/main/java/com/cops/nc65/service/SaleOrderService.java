package com.cops.nc65.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cops.entity.nc65.SaleOrder;

import java.util.List;

public interface SaleOrderService extends IService<SaleOrder> {

    List<SaleOrder> getSaleOrderAll();

    Page<SaleOrder> getPage(Page<SaleOrder> page, Wrapper wrapper);
}
