package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.scada.entity.Series;
import com.cops.scada.dao.SeriesDao;
import com.cops.scada.service.SeriesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 系列 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SeriesServiceImpl extends ServiceImpl<SeriesDao, Series> implements SeriesService {

    @Override
    public List<Series> list(Integer type) {
        EntityWrapper<Series> wrapper = new EntityWrapper<>();
        wrapper.eq("type", type);
        wrapper.eq("del_flag", false);
        return selectList(wrapper);
    }
}
