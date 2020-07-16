package com.cops.nc65.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.entity.nc65.BatchMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BatchMaterialDao extends BaseMapper<BatchMaterial> {
    /**
     * 查询批次物料信息
     * @param wrapper
     * @return
     */
    List<BatchMaterial> getBatchMaterials(@Param("ew") Wrapper wrapper);

    /**
     * 查询批次物料信息-分页
     * @param page
     * @param wrapper
     * @return
     */
    List<BatchMaterial> getPage(Page page, @Param("ew") Wrapper wrapper);
}
