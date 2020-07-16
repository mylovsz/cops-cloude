package com.cops.nc65.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cops.entity.nc65.Stordoc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StordocDao extends BaseMapper<Stordoc> {

    List<Stordoc> getStordocs(@Param("ew") Wrapper wrapper);
}
