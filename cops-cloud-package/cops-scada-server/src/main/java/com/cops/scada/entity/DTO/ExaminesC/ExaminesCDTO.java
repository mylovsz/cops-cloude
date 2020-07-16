package com.cops.scada.entity.DTO.ExaminesC;

import com.cops.scada.entity.DTO.ExaminesE.SiteInfo;

import java.util.List;

public class ExaminesCDTO {
    private SiteInfo SiteInfo;
    private List<SiteDatasC> SiteDatas;
    public void setSiteInfo(SiteInfo SiteInfo) {
        this.SiteInfo = SiteInfo;
    }
    public SiteInfo getSiteInfo() {
        return SiteInfo;
    }

    public void setSiteDatas(List<SiteDatasC> SiteDatas) {
        this.SiteDatas = SiteDatas;
    }
    public List<SiteDatasC> getSiteDatas() {
        return SiteDatas;
    }
}
