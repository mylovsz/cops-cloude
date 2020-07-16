package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.LackProduce;
import com.baomidou.mybatisplus.service.IService;
import com.cops.scada.entity.VO.LackProduceVO;

/**
 * <p>
 * 跳流程产品 服务类
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
public interface LackProduceService extends IService<LackProduce> {

    Boolean addProduce(LackProduce lackProduce);

    Page<LackProduceVO> getPageLackProduceVO(Page<LackProduceVO> page, EntityWrapper<LackProduceVO> wrapper);

    Page<LackProduceVO> getPageLackProduceVOUnion(Page<LackProduceVO> page, EntityWrapper<LackProduceVO> wrapper);

    Page<LackProduceVO> getLackProduceByProduceSn(Page<LackProduceVO> page,EntityWrapper<LackProduceVO> wrapper);
}
