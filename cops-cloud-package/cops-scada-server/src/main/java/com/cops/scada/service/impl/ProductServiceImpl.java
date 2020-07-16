package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cops.scada.entity.Product;
import com.cops.scada.dao.ProductDao;
import com.cops.scada.service.ProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 产品表 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-03-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {

    @Override
    public Boolean existsBySN(String sn) {
        EntityWrapper<Product> wrapper = new EntityWrapper<>();
        wrapper.eq("sn", sn);
        return baseMapper.exists(wrapper);
    }

    @Override
    public Boolean existsBySN(String sn, Long id) {
        EntityWrapper<Product> wrapper = new EntityWrapper<>();
        wrapper.eq("sn", sn);
        wrapper.ne("id", id);
        return baseMapper.exists(wrapper);
    }

    @Override
    public Product getOneBySn(String sn) {
        Product product = new Product();
        product.setSn(sn);
        return baseMapper.selectOne(product);
    }

    @Override
    public Product saveProduct(Product product) {
        baseMapper.insert(product);
        return selectOne(new EntityWrapper<Product>().eq("sn", product.getSn()));
    }

    //@Cacheable(value = "productAll", key = "'productAll'", unless = "#result == null or #result.size() == 0")
    @Override
    public List<Product> selectAll() {
        EntityWrapper<Product> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag", false);
        wrapper.orderBy("sn");
        List<Product> products = baseMapper.selectList(wrapper);
        return products;
    }
}
