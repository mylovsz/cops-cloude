package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.ProductInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cops.scada.entity.VO.ProductInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 展示产品 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
public interface ProductInfoDao extends BaseMapper<ProductInfo> {

    List<ProductInfoVO> getProductInfoVO(Page<ProductInfoVO> page, @Param("ew") EntityWrapper<ProductInfoVO> wrapper);
}
