package com.cops.nc65.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cops.entity.nc65.BatchMaterial;

import java.util.List;

public interface BatchMaterialService extends IService<BatchMaterial> {
    /**
     * 查询批次物料信息
     * @param wrapper
     * @return
     */
    List<BatchMaterial> getBatchMaterials(Wrapper wrapper);

    /**
     * 查询生产订单-带分页
     * @param page
     * @param wrapper
     * @return
     */
    Page<BatchMaterial> getPage(Page<BatchMaterial> page, Wrapper wrapper);
}
