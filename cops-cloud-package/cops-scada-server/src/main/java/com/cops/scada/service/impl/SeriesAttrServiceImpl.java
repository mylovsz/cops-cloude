package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.scada.entity.SeriesAndAttr;
import com.cops.scada.entity.SeriesAttr;
import com.cops.scada.dao.SeriesAttrDao;
import com.cops.scada.service.SeriesAttrService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 属性 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SeriesAttrServiceImpl extends ServiceImpl<SeriesAttrDao, SeriesAttr> implements SeriesAttrService {

    // @Cacheable(value = "'SeriesAttrService'", key = "'series_id_'+#id")
    @Override
    public List<SeriesAttr> selectBySeriesId(Long id) {
        return baseMapper.selectBySeriesId(id);
    }

    @Override
    public List<SeriesAndAttr> selectAttrIdsBySeriesId(Long id) {
        return baseMapper.selectAttrIdsBySeriesId(id);
    }

    // @CachePut(value = "'SeriesAttrService'", key = "'series_id_'+#seriesId")
    @Override
    public List<SeriesAttr> updateAttrCognate(List<SeriesAndAttr> deleteIds, List<Long> addAttrIds, List<Long> sort, Long seriesId) {
        List<Long> deleteIdList = deleteIds.stream().map(SeriesAndAttr::getId).collect(Collectors.toList());
        if (deleteIdList.size() > 0) {
            baseMapper.deleteAttrCognate(deleteIdList);
        }
        if (addAttrIds.size() > 0) {
            List<SeriesAndAttr> addList = new ArrayList<>();
            for (Long attrid : addAttrIds) {
                for (int i = 0; i < sort.size(); i++) {
                    if (sort.get(i) == attrid) {
                        SeriesAndAttr seriesAndAttr = new SeriesAndAttr();
                        seriesAndAttr.setSeriesAttrId(attrid);
                        seriesAndAttr.setSeriesId(seriesId);
                        seriesAndAttr.setSort((long)i);
                        addList.add(seriesAndAttr);
                    }
                }
            }
            baseMapper.addAttrCogonate(addList);
        }

        return baseMapper.selectBySeriesId(seriesId);
    }

    @Override
    public List<SeriesAttr> listForSearchByType(Integer type) {
        EntityWrapper<SeriesAttr> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag", false);
        wrapper.eq("type", type);
        wrapper.eq("is_search", 1);
        return selectList(wrapper);
    }

    @Override
    public List<SeriesAttr> listForSearchBySeriesId(Long seriesId) {
        EntityWrapper<SeriesAttr> wrapper = new EntityWrapper<>();
        wrapper.eq("series_and_attr.series_id", seriesId);
        wrapper.eq("series_attr.is_search", 1);
        wrapper.eq("series_attr.del_flag", 0);
        wrapper.orderBy("series_and_attr.sort");
        return this.baseMapper.listBy(wrapper);
    }

    @Override
    public List<SeriesAttr> listBySeriesId(Long seriesId) {
        EntityWrapper<SeriesAttr> wrapper = new EntityWrapper<>();
        wrapper.eq("series_and_attr.series_id", seriesId);
        wrapper.eq("series_attr.del_flag", 0);
        wrapper.orderBy("series_and_attr.sort");
        return this.baseMapper.listBy(wrapper);
    }

    @Override
    public void updateSeriesAndAttrSort(List<SeriesAndAttr> updateSeriesAndAttr) {
        baseMapper.updateSeriesAndAttrSort(updateSeriesAndAttr);
    }
}
