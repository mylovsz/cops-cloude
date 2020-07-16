package com.cops.scada.entity.DTO.ExaminesF;

import com.cops.scada.entity.DTO.ExaminesB.SiteDatasB;
import com.cops.scada.entity.DTO.ExaminesE.SiteInfo;

import java.util.List;

public class ExaminesFDTO {
    private SiteInfo SiteInfo;
    private List<SiteDatasF> SiteDatas;
    public void setSiteInfo(SiteInfo SiteInfo) {
        this.SiteInfo = SiteInfo;
    }
    public SiteInfo getSiteInfo() {
        return SiteInfo;
    }

    public void setSiteDatas(List<SiteDatasF> SiteDatas) {
        this.SiteDatas = SiteDatas;
    }
    public List<SiteDatasF> getSiteDatas() {
        return SiteDatas;
    }
}
