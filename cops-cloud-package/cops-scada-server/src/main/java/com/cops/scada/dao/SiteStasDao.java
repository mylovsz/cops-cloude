package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.VO.SiteStasVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SiteStasDao extends BaseMapper<SiteStasVO> {

    List<SiteStasVO> getSiteStasTypeCount(@Param("tabname") String tabname,Page<SiteStasVO> page, @Param("ew") EntityWrapper<SiteStasVO> wrapper);

    List<SiteStasVO> getSiteStasTypeCount2(@Param("tabname") String tabname,@Param("ew") EntityWrapper<SiteStasVO> wrapper);

    List<SiteStasVO> getSiteStasALLTestRecordCount(@Param("tabname") String tabname,@Param("ew") EntityWrapper<SiteStasVO> wrapper);

    List<SiteStasVO> getSiteStasTestOKRecordCount(@Param("tabname") String tabname,@Param("ew") EntityWrapper<SiteStasVO> wrapper);

    List<SiteStasVO> getSiteStasTestFailRecordCount(@Param("tabname") String tabname,@Param("ew") EntityWrapper<SiteStasVO> wrapper);

    List<SiteStasVO> getSiteStasTestRedoRecordCount(@Param("tabname") String tabname,@Param("ew") EntityWrapper<SiteStasVO> wrapper);
}
