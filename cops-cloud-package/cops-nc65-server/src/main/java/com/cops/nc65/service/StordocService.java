package com.cops.nc65.service;

import com.baomidou.mybatisplus.service.IService;
import com.cops.entity.nc65.Stordoc;

import com.baomidou.mybatisplus.mapper.Wrapper;
import java.util.List;

public interface StordocService extends IService<Stordoc> {
    List<Stordoc> getStordocs(Wrapper wrapper);
}
