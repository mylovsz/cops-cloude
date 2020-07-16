package com.cops.nc65.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cops.entity.nc65.Bom;
import com.cops.entity.nc65.BomOnhand;
import com.cops.entity.nc65.BomQueryV;
import com.cops.entity.nc65.SupplierPrice;
import com.cops.nc65.dao.BomDao;
import com.cops.nc65.dao.SupplierPriceDao;
import com.cops.nc65.service.BomService;
import com.cops.nc65.service.SupplierPriceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SupplierPriceServiceImpl extends ServiceImpl<SupplierPriceDao, SupplierPrice> implements SupplierPriceService {

    @Override
    public List<SupplierPrice> getSupplierPrice(Wrapper wrapper) {
        return this.baseMapper.getSupplierPrice(wrapper);
    }

    @Override
    public SupplierPrice getSupplierPrice(String materialCode) {
        EntityWrapper<SupplierPrice> wrapper = new EntityWrapper<>();
        wrapper.eq("supplierprice.dr", 0);
        //wrapper.eq("supplierprice.fpricesrctype", 1);
        wrapper.eq("material.code", materialCode);
        wrapper.orderBy("supplierprice.dvaliddate", false);
        List<SupplierPrice> ls = this.baseMapper.getSupplierPrice(wrapper);
        if(ls != null && ls.size() > 0){
            return ls.get(0);
        }
        return null;
    }
}
