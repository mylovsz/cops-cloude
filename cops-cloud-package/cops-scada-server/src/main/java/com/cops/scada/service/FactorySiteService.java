package com.cops.scada.service;

import com.cops.scada.entity.FactorySite;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 车间站点信息 服务类
 * </p>
 *
 * @author wanglm
 * @since 2019-04-15
 */
public interface FactorySiteService extends IService<FactorySite> {

    List<FactorySite> getListForTree();

    void saveOrUpdateChannel(FactorySite factorySite);
}
