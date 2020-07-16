package com.cops.nc65.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cops.entity.nc65.ProductionOrder;

import java.util.List;

public interface ProductionOrderService extends IService<ProductionOrder> {
    /**
     * 查询生产订单
     * @param wrapper
     * @return
     */
    List<ProductionOrder> getProductionOrders(Wrapper wrapper);

    /**
     * 查询生产订单-带分页
     * @param page
     * @param wrapper
     * @return
     */
    Page<ProductionOrder> getPage(Page<ProductionOrder> page, Wrapper wrapper);
}
