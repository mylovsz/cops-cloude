package com.cops.nc65.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cops.entity.nc65.Stordoc;
import com.cops.nc65.dao.StordocDao;
import com.cops.nc65.service.StordocService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class StordocServiceImpl extends ServiceImpl<StordocDao, Stordoc> implements StordocService {
    @Override
    public List<Stordoc> getStordocs(Wrapper wrapper) {
        return this.baseMapper.getStordocs(wrapper);
    }
}
