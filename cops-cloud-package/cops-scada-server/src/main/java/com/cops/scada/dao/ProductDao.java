package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.scada.entity.Product;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cops.scada.entity.VO.ProduceVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 产品表 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2019-03-11
 */
public interface ProductDao extends BaseMapper<Product> {

    Boolean exists(@Param("ew") EntityWrapper<Product> wrapper);

}
