package com.cops.scada.entity.DTO.ExaminesA;

import com.cops.scada.entity.DTO.ExaminesE.SiteInfo;
import lombok.Data;

import java.util.List;

@Data
public class ExaminesADTO {
    private SiteInfo SiteInfo;
    private List<SiteDatasA> SiteDatas;
}
