package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.ProductInfoAttrValue;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cops.scada.entity.VO.ProductInfoAttrAndValueVO;
import com.cops.scada.entity.VO.ProductInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 展示产品属性值 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
public interface ProductInfoAttrValueDao extends BaseMapper<ProductInfoAttrValue> {

    List<ProductInfoAttrAndValueVO> listBy(@Param("ew") EntityWrapper<ProductInfoAttrAndValueVO> wrapper);

    /**
     * 用于获取条件
     * @param wrapper
     * @return
     */
    List<ProductInfoAttrValue> listForConditionBy(@Param("ew") EntityWrapper<ProductInfoAttrValue> wrapper);

    /**
     * 获取nameid的集合
     * @param wrapper
     * @return
     */
    List<Long> listForNameId(@Param("ew") EntityWrapper<ProductInfoAttrValue> wrapper);
}
