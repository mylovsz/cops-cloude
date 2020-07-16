package com.cops.nc65.service;


import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cops.entity.nc65.SaleOrder;
import com.cops.entity.nc65.SaleOrderDetail;


import java.util.List;

public interface SaleOrderDetailService extends IService<SaleOrderDetail> {
    List<SaleOrderDetail> getSaleOrderDetailList();

    List<SaleOrderDetail> getSaleOrderDetails(Wrapper wrapper);
}
