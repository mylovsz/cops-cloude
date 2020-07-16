package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.LackProduce;
import com.cops.scada.dao.LackProduceDao;
import com.cops.scada.entity.VO.LackProduceVO;
import com.cops.scada.service.LackProduceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 跳流程产品 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LackProduceServiceImpl extends ServiceImpl<LackProduceDao, LackProduce> implements LackProduceService {

    @Override
    public Boolean addProduce(LackProduce lackProduce) {
        return this.baseMapper.insert(lackProduce)>0;
    }

    @Override
    public Page<LackProduceVO> getPageLackProduceVO(Page<LackProduceVO> page, EntityWrapper<LackProduceVO> wrapper) {
        return page.setRecords(this.baseMapper.getPageLackProduceVO(page, wrapper));
    }

    @Override
    public Page<LackProduceVO> getPageLackProduceVOUnion(Page<LackProduceVO> page, EntityWrapper<LackProduceVO> wrapper) {
        return page.setRecords(this.baseMapper.getPageLackProduceVOUnion(page, wrapper));
    }

    @Override
    public Page<LackProduceVO> getLackProduceByProduceSn(Page<LackProduceVO> page,EntityWrapper<LackProduceVO> wrapper) {
        return page.setRecords(this.baseMapper.getLackProduceByProduceSn(page, wrapper));
    }
}
