package com.cops.nc65.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cops.entity.nc65.Bom;
import com.cops.entity.nc65.BomOnhand;
import com.cops.entity.nc65.BomQueryV;
import com.cops.nc65.dao.BomDao;
import com.cops.nc65.service.BomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BomServiceImpl extends ServiceImpl<BomDao, Bom> implements BomService {
    @Override
    public List<BomOnhand> getBomOnhand(Wrapper wrapper, Double num) {
        return this.baseMapper.getBomOnhand(wrapper, num);
    }

    @Override
    public Page<BomOnhand> getBomOnhandPage(Page<BomOnhand> page, Wrapper wrapper) {
        return page.setRecords(this.baseMapper.getBomOnhandPage(page, wrapper));
    }

    @Override
    public List<BomQueryV> getBomQueryV(String hcmaterialid) {
        return this.baseMapper.getBomQueryV(hcmaterialid);
    }

    @Override
    public List<Bom> getBom(Wrapper wrapper) {
        return this.baseMapper.getBom(wrapper);
    }
}
