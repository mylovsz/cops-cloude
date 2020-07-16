package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.scada.entity.ProductInfoAttrValue;
import com.baomidou.mybatisplus.service.IService;
import com.cops.scada.entity.VO.ProductInfoAttrAndValueVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 展示产品属性值 服务类
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
public interface ProductInfoAttrValueService extends IService<ProductInfoAttrValue> {

    ProductInfoAttrValue getProductInfoAttrByProductInfoIdAndAttrId(Long productInfoId , Long seriesAttrId);

    /**
     * 返回该产品的属性和对于的实际值
     * @param productInfoId
     * @return
     */
    List<ProductInfoAttrAndValueVO> listByProductInfoId(Long productInfoId);

    /**
     * 根据系列的ID，获取当前系统存在的数据
     * @param seriesAttrId
     * @return
     */
    List<ProductInfoAttrValue> listForConditionBySeriesAttrId(Long seriesAttrId, Long seriesId);

    List<ProductInfoAttrValue> listForConditionBySeriesAttrId(Long seriesAttrId);

    /**
     * 获取name id 集合
     * @param seriesAttrId
     * @param value
     * @param valueEn
     * @return
     */
    List<Long> listForNameId(Long seriesAttrId, String value, String valueEn);
}
