package com.cops.nc65.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.entity.nc65.ProductionOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductionOrderDao extends BaseMapper<ProductionOrder> {
    /**
     * 查询生产订单
     * @param wrapper
     * @return
     */
    List<ProductionOrder> getProductionOrders(@Param("ew") Wrapper wrapper);

    /**
     * 查询生产订单-分页
     * @param page
     * @param wrapper
     * @return
     */
    List<ProductionOrder> getPage(Page page, @Param("ew") Wrapper wrapper);
}
