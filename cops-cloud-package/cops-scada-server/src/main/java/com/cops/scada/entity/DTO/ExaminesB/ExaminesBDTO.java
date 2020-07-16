package com.cops.scada.entity.DTO.ExaminesB;

import com.cops.scada.entity.DTO.ExaminesE.SiteInfo;

import java.util.List;

public class ExaminesBDTO {
    private SiteInfo SiteInfo;
    private List<SiteDatasB> SiteDatas;
    public void setSiteInfo(SiteInfo SiteInfo) {
        this.SiteInfo = SiteInfo;
    }
    public SiteInfo getSiteInfo() {
        return SiteInfo;
    }

    public void setSiteDatas(List<SiteDatasB> SiteDatas) {
        this.SiteDatas = SiteDatas;
    }
    public List<SiteDatasB> getSiteDatas() {
        return SiteDatas;
    }
}
