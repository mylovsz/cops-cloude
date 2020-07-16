package com.cops.scada.dao;

import com.cops.scada.entity.FactorySite;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车间站点信息 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2019-04-15
 */
public interface FactorySiteDao extends BaseMapper<FactorySite> {

    List<FactorySite> getListForTree(Map<String,Object> map);

}
