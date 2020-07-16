package com.cops.scada.entity.DTO.ExaminesD;

import com.cops.scada.entity.DTO.ExaminesA.SiteDatasA;
import com.cops.scada.entity.DTO.ExaminesE.SiteInfo;
import lombok.Data;

import java.util.List;

@Data
public class ExaminesDDTO {
    private SiteInfo SiteInfo;
    private List<SiteDatasD> SiteDatas;
}
