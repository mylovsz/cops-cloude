package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.LackProduce;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cops.scada.entity.VO.LackProduceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 跳流程产品 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
public interface LackProduceDao extends BaseMapper<LackProduce> {
    List<LackProduceVO> getPageLackProduceVO(Page<LackProduceVO> page, @Param("ew") EntityWrapper<LackProduceVO> wrapper);

    List<LackProduceVO> getPageLackProduceVOUnion(Page<LackProduceVO> page, @Param("ew") EntityWrapper<LackProduceVO> wrapper);

    List<LackProduceVO> getLackProduceByProduceSn(Page<LackProduceVO> page, @Param("ew") EntityWrapper<LackProduceVO> wrapper);
}
