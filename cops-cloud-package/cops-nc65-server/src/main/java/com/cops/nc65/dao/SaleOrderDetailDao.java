package com.cops.nc65.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.entity.nc65.SaleOrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleOrderDetailDao extends BaseMapper<SaleOrderDetail> {
    List<SaleOrderDetail> getSaleOrderDetail();//测试

    List<SaleOrderDetail> getSaleOrderDetails(@Param("ew") Wrapper wrapper);
}
