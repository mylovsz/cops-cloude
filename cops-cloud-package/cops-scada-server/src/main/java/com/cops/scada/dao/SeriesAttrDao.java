package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.scada.entity.SeriesAndAttr;
import com.cops.scada.entity.SeriesAttr;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 属性 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
public interface SeriesAttrDao extends BaseMapper<SeriesAttr> {

    /**
     * 根据条件进行查询
     * @param wrapper
     * @return
     */
    List<SeriesAttr> listBy(@Param("ew") EntityWrapper<SeriesAttr> wrapper);

    List<SeriesAttr> selectBySeriesId(@Param("id") Long id);

    List<SeriesAndAttr> selectAttrIdsBySeriesId(@Param("id") Long id);

    int deleteAttrCognate(@Param("ids") List<Long> deleteIdList);

    int addAttrCogonate(@Param("models") List<SeriesAndAttr> addAttrIds);

    void updateSeriesAndAttrSort(@Param("models") List<SeriesAndAttr> updateSeriesAndAttr);
}
