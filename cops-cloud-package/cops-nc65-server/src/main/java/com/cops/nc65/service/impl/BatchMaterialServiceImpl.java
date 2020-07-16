package com.cops.nc65.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cops.entity.nc65.BatchMaterial;
import com.cops.nc65.dao.BatchMaterialDao;
import com.cops.nc65.service.BatchMaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BatchMaterialServiceImpl extends ServiceImpl<BatchMaterialDao, BatchMaterial> implements BatchMaterialService {
    @Override
    public List<BatchMaterial> getBatchMaterials(Wrapper wrapper) {
        return null;
    }

    @Override
    public Page<BatchMaterial> getPage(Page<BatchMaterial> page, Wrapper wrapper) {
        return page.setRecords(this.baseMapper.getPage(page, wrapper));
    }
}
