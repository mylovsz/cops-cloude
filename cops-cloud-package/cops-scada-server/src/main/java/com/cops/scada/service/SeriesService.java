package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cops.scada.entity.Series;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 系列 服务类
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
public interface SeriesService extends IService<Series> {

    /**
     * 获取该类型的所有系列
     * @param type
     * @return
     */
    List<Series> list(Integer type);
}
