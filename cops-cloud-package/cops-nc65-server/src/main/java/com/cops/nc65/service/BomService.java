package com.cops.nc65.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cops.entity.nc65.Bom;
import com.cops.entity.nc65.BomOnhand;
import com.cops.entity.nc65.BomQueryV;

import java.util.List;

public interface BomService extends IService<Bom> {

    List<BomOnhand> getBomOnhand(Wrapper wrapper, Double num);

    Page<BomOnhand> getBomOnhandPage(Page<BomOnhand> page, Wrapper wrapper);

    List<BomQueryV> getBomQueryV(String hcmaterialid);

    List<Bom> getBom(Wrapper wrapper);
}
