package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.Produce;
import com.cops.scada.dao.ProduceDao;
import com.cops.scada.entity.Product;
import com.cops.scada.entity.VO.ProduceVO;
import com.cops.scada.entity.VO.StatisticsProduceVO;
import com.cops.scada.service.ProduceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 生产产品 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-03-19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProduceServiceImpl extends ServiceImpl<ProduceDao, Produce> implements ProduceService {

    @Override
    public Boolean existsBySN(String sn) {
        EntityWrapper<Produce> wrapper = new EntityWrapper<>();
        wrapper.eq("sn", sn);
        return baseMapper.exists(wrapper);
    }

    @Override
    public StatisticsProduceVO getStatisticsProduceVO() {
//        EntityWrapper<StatisticsProduceVO> wrapper = new EntityWrapper<>();
//        wrapper.eq("plan.del_flag", false);
//        wrapper.eq("produce.del_flag", false);
        return this.baseMapper.getStatisticsProduceVO();
    }

    @Override
    public StatisticsProduceVO getStatisticsProduceVO(Integer productType) {
        return this.baseMapper.getStatisticsProduceVOByProductType(productType);
    }

    @Override
    public StatisticsProduceVO getStatisticsProduceVO(Integer productType, Date startDate, Date endDate) {
        return this.baseMapper.getStatisticsProduceVOByTypeAndDate(productType, startDate, endDate);
    }

    //    @Cacheable(value = "produce", key = "'produce_sn_'+#sn",unless = "#result == null")
    @Override
    public Produce getOneBySN(String sn) {
        Produce produce = new Produce();
        produce.setSn(sn);
        return this.baseMapper.selectOne(produce);
    }

    @Override
    public Produce getOneByID(Long id) {
        return selectById(id);
    }

    //    @Caching(put = {
//            @CachePut(value = "produce", key = "'produce_sn_'+#produce.sn", condition = "#produce.sn !=null and #produce.sn != ''")
//    })
    @Override
    public Produce saveProduce(Produce produce) {
        this.baseMapper.updateById(produce);
        return getOneBySN(produce.getSn());
    }

    @Override
    public ProduceVO getProduceVOBySN(String sn) {
        EntityWrapper<ProduceVO> wrapper = new EntityWrapper<>();
        wrapper.eq("produce.sn", sn);
        return this.baseMapper.getProduceVO(wrapper);
    }

    @Override
    public Page<ProduceVO> getProduceVO(Page<ProduceVO> page, EntityWrapper<ProduceVO> wrapper) {
        return page.setRecords(this.baseMapper.getProduceVO(page, wrapper));
    }

    /**
     * 随机获取一个未处理过的产品
     *
     * @param c  类型
     * @param id plan id
     * @return 产品
     */
    @Override
    public Produce getOneByPlanSN(String c, Long id) {
        EntityWrapper<Produce> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("plan_id", id);

        switch (c) {
            case "C":
                entityWrapper.eq("result_c", 0);
                break;
            default:
                return null;
        }
        return baseMapper.getOneByPlanSN(entityWrapper);
    }
}
