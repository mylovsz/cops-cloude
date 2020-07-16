package com.cops.scada.entity.DTO.ExaminesK;

import com.cops.scada.entity.DTO.ExaminesE.SiteInfo;
import com.cops.scada.entity.DTO.ExaminesF.SiteDatasF;

import java.util.List;

public class ExaminesKDTO {
    private SiteInfo SiteInfo;
    private List<SiteDataK> SiteDatas;
    public void setSiteInfo(SiteInfo SiteInfo) {
        this.SiteInfo = SiteInfo;
    }
    public SiteInfo getSiteInfo() {
        return SiteInfo;
    }

    public void setSiteDatas(List<SiteDataK> SiteDatas) {
        this.SiteDatas = SiteDatas;
    }
    public List<SiteDataK> getSiteDatas() {
        return SiteDatas;
    }
}
