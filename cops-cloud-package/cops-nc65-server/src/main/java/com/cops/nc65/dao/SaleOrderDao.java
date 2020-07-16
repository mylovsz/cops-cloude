package com.cops.nc65.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.entity.nc65.SaleOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleOrderDao extends BaseMapper<SaleOrder> {
    List<SaleOrder> getAll();

    List<SaleOrder> getPage(Page page, @Param("ew") Wrapper wrapper);
}
