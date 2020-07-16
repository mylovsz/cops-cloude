package com.cops.nc65.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cops.entity.nc65.Bom;
import com.cops.entity.nc65.BomOnhand;
import com.cops.entity.nc65.BomQueryV;
import com.cops.entity.nc65.SupplierPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierPriceService extends IService<SupplierPrice> {
    /**
     * 获取价格，根据物料
     * @param wrapper
     * @return
     */
    List<SupplierPrice> getSupplierPrice(@Param("ew") Wrapper wrapper);

    /**
     * 获取价格，根据物料ID
     * @param materialCode
     * @return
     */
    SupplierPrice getSupplierPrice(String materialCode);
}
