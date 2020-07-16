package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.Produce;
import com.baomidou.mybatisplus.service.IService;
import com.cops.scada.entity.VO.ProduceVO;
import com.cops.scada.entity.VO.StatisticsProduceVO;

import java.util.Date;

/**
 * <p>
 * 生产产品 服务类
 * </p>
 *
 * @author wanglm
 * @since 2019-03-19
 */
public interface ProduceService extends IService<Produce> {

    Boolean existsBySN(String sn);

    StatisticsProduceVO getStatisticsProduceVO();

    StatisticsProduceVO getStatisticsProduceVO(Integer productType);

    StatisticsProduceVO getStatisticsProduceVO(Integer productType, Date startDate, Date endDate);

    Produce getOneBySN(String sn);

    Produce getOneByID(Long id);

    Produce saveProduce(Produce produce);

    ProduceVO getProduceVOBySN(String sn);

    Page<ProduceVO> getProduceVO(Page<ProduceVO> page, EntityWrapper<ProduceVO> wrapper);

    Produce getOneByPlanSN(String c, Long id);
}
