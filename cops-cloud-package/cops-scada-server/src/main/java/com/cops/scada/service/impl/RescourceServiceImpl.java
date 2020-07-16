package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.scada.dao.RescourceDao;
import com.cops.scada.entity.Rescource;
import com.cops.scada.service.RescourceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统资源 服务实现类
 * </p>
 *
 * @author wangl
 * @since 2018-01-14
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class RescourceServiceImpl extends ServiceImpl<RescourceDao, Rescource> implements RescourceService {

    @Override
    public int getCountByHash(String hash) {
        EntityWrapper<Rescource> wrapper = new EntityWrapper<>();
        wrapper.eq("hash",hash);
        return selectCount(wrapper);
    }

    @Override
    public Rescource getRescourceByHash(String hash) {
        EntityWrapper<Rescource> wrapper = new EntityWrapper<>();
        wrapper.eq("hash",hash);
        return selectOne(wrapper);
    }
}
