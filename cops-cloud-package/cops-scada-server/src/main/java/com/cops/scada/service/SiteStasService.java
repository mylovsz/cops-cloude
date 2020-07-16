package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cops.scada.entity.VO.SiteStasVO;

import java.util.List;

public interface SiteStasService extends IService<SiteStasVO> {

    Page<SiteStasVO> getSiteStasTypeCount(String tabname,Page<SiteStasVO> page, EntityWrapper<SiteStasVO> wrapper);

    List<SiteStasVO> getSiteStasTypeCount2(String tabname,EntityWrapper<SiteStasVO> wrapper);

    List<SiteStasVO> getSiteStasALLTestRecordCount(String tabname,EntityWrapper<SiteStasVO> wrapper);

    List<SiteStasVO> getSiteStasTestOKRecordCount(String tabname,EntityWrapper<SiteStasVO> wrapper);

    List<SiteStasVO> getSiteStasTestFailRecordCount(String tabname,EntityWrapper<SiteStasVO> wrapper);

    List<SiteStasVO> getSiteStasTestRedoRecordCount(String tabname,EntityWrapper<SiteStasVO> wrapper);

}
