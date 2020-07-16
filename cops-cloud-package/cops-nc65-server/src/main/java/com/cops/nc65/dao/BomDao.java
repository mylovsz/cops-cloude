package com.cops.nc65.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.entity.nc65.Bom;
import com.cops.entity.nc65.BomOnhand;
import com.cops.entity.nc65.BomQueryV;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BomDao extends BaseMapper<Bom> {

    List<BomOnhand> getBomOnhandPage(Page page, @Param("ew") Wrapper wrapper);

    List<BomOnhand> getBomOnhand(@Param("ew") Wrapper wrapper, @Param("num") Double num);

    List<BomQueryV> getBomQueryV(@Param("hcmaterialid") String hcmaterialid);

    List<Bom> getBom(@Param("ew") Wrapper wrapper);
}
