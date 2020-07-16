package com.cops.scada.service.impl;

import com.cops.scada.entity.FactorySite;
import com.cops.scada.dao.FactorySiteDao;
import com.cops.scada.service.FactorySiteService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车间站点信息 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-04-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FactorySiteServiceImpl extends ServiceImpl<FactorySiteDao, FactorySite> implements FactorySiteService {

    @Override
    public List<FactorySite> getListForTree() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("parentId", null);
        List<FactorySite> list = Lists.newArrayList();
        try {
            list = baseMapper.getListForTree(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void saveOrUpdateChannel(FactorySite factorySite) {
        try{
            insertOrUpdate(factorySite);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
