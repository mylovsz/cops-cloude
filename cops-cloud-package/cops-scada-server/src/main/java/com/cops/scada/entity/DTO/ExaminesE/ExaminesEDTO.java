package com.cops.scada.entity.DTO.ExaminesE;

import java.util.List;

/**
 * 点火数据
 */
public class ExaminesEDTO {

    private SiteInfo SiteInfo;
    private List<SiteDatas> SiteDatas;

    public void setSiteInfo(SiteInfo SiteInfo) {
        this.SiteInfo = SiteInfo;
    }

    public SiteInfo getSiteInfo() {
        return SiteInfo;
    }

    public void setSiteDatas(List<SiteDatas> SiteDatas) {
        this.SiteDatas = SiteDatas;
    }

    public List<SiteDatas> getSiteDatas() {
        return SiteDatas;
    }


}
