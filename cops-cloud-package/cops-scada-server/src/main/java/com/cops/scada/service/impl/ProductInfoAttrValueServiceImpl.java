package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.scada.entity.ProductInfoAttrValue;
import com.cops.scada.dao.ProductInfoAttrValueDao;
import com.cops.scada.entity.VO.ProductInfoAttrAndValueVO;
import com.cops.scada.service.ProductInfoAttrValueService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 展示产品属性值 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductInfoAttrValueServiceImpl extends ServiceImpl<ProductInfoAttrValueDao, ProductInfoAttrValue> implements ProductInfoAttrValueService {

    @Override
    public ProductInfoAttrValue getProductInfoAttrByProductInfoIdAndAttrId(Long projectInfoId, Long seriesAttrId) {
        ProductInfoAttrValue productInfoAttrValue = new ProductInfoAttrValue();
        productInfoAttrValue.setSeriesAttrId(seriesAttrId);
        productInfoAttrValue.setProductInfoId(projectInfoId);
        return baseMapper.selectOne(productInfoAttrValue);
    }

    @Override
    public List<ProductInfoAttrAndValueVO> listByProductInfoId(Long productInfoId) {
        EntityWrapper<ProductInfoAttrAndValueVO> productInfoAttrValueEntityWrapper = new EntityWrapper<>();
        productInfoAttrValueEntityWrapper.eq("product_info_attr_value.product_info_id", productInfoId);
        return this.baseMapper.listBy(productInfoAttrValueEntityWrapper);
    }

    @Override
    public List<ProductInfoAttrValue> listForConditionBySeriesAttrId(Long seriesAttrId, Long seriesId) {
        EntityWrapper<ProductInfoAttrValue> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect("product_info_attr_value.value", "product_info_attr_value.value_en");
        wrapper.eq("product_info_attr_value.series_attr_id", seriesAttrId);
        wrapper.eq("product_info.series_id", seriesId);
        wrapper.groupBy("product_info_attr_value.value").groupBy("product_info_attr_value.value_en");
        return this.baseMapper.listForConditionBy(wrapper);
    }

    @Override
    public List<ProductInfoAttrValue> listForConditionBySeriesAttrId(Long seriesAttrId) {
        EntityWrapper<ProductInfoAttrValue> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect("value", "value_en");
        wrapper.eq("series_attr_id", seriesAttrId);
        wrapper.groupBy("value").groupBy("value_en");
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Long> listForNameId(Long seriesAttrId, String value, String valueEn) {
        EntityWrapper<ProductInfoAttrValue> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect("product_info_id");
        wrapper.eq("series_attr_id", seriesAttrId);
        wrapper.eq("value", value);
        if(valueEn!= null) {
            wrapper.eq("value_en", valueEn);
        }
        return (this.baseMapper.listForNameId(wrapper));
    }
}
