package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cops.scada.dao.SiteStasDao;
import com.cops.scada.entity.VO.SiteStasVO;
import com.cops.scada.service.SiteStasService;
import com.cops.scada.util.CloneUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteStasServiceImpl extends ServiceImpl<SiteStasDao, SiteStasVO> implements SiteStasService {

    @Override
    public Page<SiteStasVO> getSiteStasTypeCount(String tabname,Page<SiteStasVO> page, EntityWrapper<SiteStasVO> wrapper) {
        return page.setRecords(this.baseMapper.getSiteStasTypeCount(tabname,page,wrapper));
    }

    @Override
    public List<SiteStasVO> getSiteStasTypeCount2(String tabname, EntityWrapper<SiteStasVO> wrapper) {

        return this.baseMapper.getSiteStasTypeCount2(tabname,wrapper);
    }

    @Override
    public List<SiteStasVO> getSiteStasALLTestRecordCount(String tabname, EntityWrapper<SiteStasVO> wrapper) {

        return this.baseMapper.getSiteStasALLTestRecordCount(tabname,wrapper);
    }

    @Override
    public List<SiteStasVO> getSiteStasTestOKRecordCount(String tabname, EntityWrapper<SiteStasVO> wrapper) {
        EntityWrapper<SiteStasVO> a = (EntityWrapper<SiteStasVO>) CloneUtil.clone(wrapper);
        a.eq("tab1.result", "PASS");
        return this.baseMapper.getSiteStasTestOKRecordCount(tabname,a);
    }

    @Override
    public List<SiteStasVO> getSiteStasTestFailRecordCount(String tabname, EntityWrapper<SiteStasVO> wrapper) {
        EntityWrapper<SiteStasVO> a = (EntityWrapper<SiteStasVO>) CloneUtil.clone(wrapper);
        a.ne("tab1.result", "PASS");
        return this.baseMapper.getSiteStasTestFailRecordCount(tabname,a);
    }

    @Override
    public List<SiteStasVO> getSiteStasTestRedoRecordCount(String tabname, EntityWrapper<SiteStasVO> wrapper) {
        return this.baseMapper.getSiteStasTestRedoRecordCount(tabname,wrapper);
    }
}
