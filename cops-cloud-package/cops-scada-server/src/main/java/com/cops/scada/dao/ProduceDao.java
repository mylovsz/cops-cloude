package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.Produce;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cops.scada.entity.Product;
import com.cops.scada.entity.VO.PlanProductVO;
import com.cops.scada.entity.VO.ProduceVO;
import com.cops.scada.entity.VO.StatisticsProduceVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 生产产品 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2019-03-19
 */
public interface ProduceDao extends BaseMapper<Produce> {

    /**
     * 查询产品的统计信息
     * @param wrapper
     * @return
     */
    StatisticsProduceVO getStatisticsProduceVO();

    /**
     * 查询产品的统计信息
     * @param wrapper
     * @return
     */
    StatisticsProduceVO getStatisticsProduceVOByProductType(@Param("productType") Integer productType);

    StatisticsProduceVO getStatisticsProduceVOByTypeAndDate(
            @Param("productType") Integer productType,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

    Boolean exists(@Param("ew") EntityWrapper<Produce> wrapper);

    ProduceVO getProduceVO(@Param("ew") EntityWrapper<ProduceVO> wrapper);

    List<ProduceVO> getProduceVO(Page<ProduceVO> page, @Param("ew") EntityWrapper<ProduceVO> wrapper);

    Produce getOneByPlanSN(@Param("ew")  EntityWrapper<Produce> entityWrapper);
}
