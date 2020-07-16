package com.cops.scada.service;

import com.cops.scada.entity.Product;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 产品表 服务类
 * </p>
 *
 * @author wanglm
 * @since 2019-03-11
 */
public interface ProductService extends IService<Product> {

    Boolean existsBySN(String sn);

    Boolean existsBySN(String sn, Long id);

    Product getOneBySn(String sn);

    Product saveProduct(Product product);

    List<Product> selectAll();
}
