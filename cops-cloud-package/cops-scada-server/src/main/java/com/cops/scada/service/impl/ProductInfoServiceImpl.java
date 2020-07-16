package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.ProductInfo;
import com.cops.scada.dao.ProductInfoDao;
import com.cops.scada.entity.VO.ProductInfoVO;
import com.cops.scada.service.ProductInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 展示产品 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoDao, ProductInfo> implements ProductInfoService {

    @Override
    public Page<ProductInfoVO> getProductInfoVO(Page<ProductInfoVO> page, EntityWrapper<ProductInfoVO> wrapper) {
        return page.setRecords(this.baseMapper.getProductInfoVO(page, wrapper));
    }
}
