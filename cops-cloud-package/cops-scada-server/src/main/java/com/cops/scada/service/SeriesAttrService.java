package com.cops.scada.service;

import com.cops.scada.entity.SeriesAndAttr;
import com.cops.scada.entity.SeriesAttr;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 属性 服务类
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
public interface SeriesAttrService extends IService<SeriesAttr> {

    List<SeriesAttr> selectBySeriesId(Long id);

    List<SeriesAndAttr> selectAttrIdsBySeriesId(Long id);

    List<SeriesAttr> updateAttrCognate(List<SeriesAndAttr> deleteIds, List<Long> addAttrIds,List<Long> sort,Long seriesId);

    /**
     * 获取指定类型的系列
     * @param type
     * @return
     */
    List<SeriesAttr> listForSearchByType(Integer type);

    /**
     * 根据系列ID获取在用的条件
     * @param seriesId
     * @return
     */
    List<SeriesAttr> listForSearchBySeriesId(Long seriesId);

    /**
     * 根据系列的ID获取属性
     * @param seriesId
     * @return
     */
    List<SeriesAttr> listBySeriesId(Long seriesId);

    void updateSeriesAndAttrSort(List<SeriesAndAttr> updateSeriesAndAttr);
}
