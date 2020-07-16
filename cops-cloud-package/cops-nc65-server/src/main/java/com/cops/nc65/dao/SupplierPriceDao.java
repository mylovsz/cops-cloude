package com.cops.nc65.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cops.entity.nc65.SupplierPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 获取价格相关内容
 * @author wanglm
 */
public interface SupplierPriceDao extends BaseMapper<SupplierPrice> {

    /**
     * 获取价格，根据物料
     * @param wrapper
     * @return
     */
    List<SupplierPrice> getSupplierPrice(@Param("ew") Wrapper wrapper);
}
