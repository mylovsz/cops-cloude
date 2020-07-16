package com.cops.scada.entity.DTO.ExaminesH;

import lombok.Data;

import java.util.List;

@Data
public class ExaminesHDTO {
    private com.cops.scada.entity.DTO.ExaminesE.SiteInfo SiteInfo;
    private List<SiteDatasH> SiteDatas;
}
