package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.ProductInfo;
import com.baomidou.mybatisplus.service.IService;
import com.cops.scada.entity.VO.ProductInfoVO;

/**
 * <p>
 * 展示产品 服务类
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
public interface ProductInfoService extends IService<ProductInfo> {

    /**
     * 获取系列信息
     * @param page
     * @param wrapper
     * @return
     */
    Page<ProductInfoVO> getProductInfoVO(Page<ProductInfoVO> page, EntityWrapper<ProductInfoVO> wrapper);

}
