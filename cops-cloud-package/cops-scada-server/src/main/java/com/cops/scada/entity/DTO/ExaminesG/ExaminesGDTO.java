package com.cops.scada.entity.DTO.ExaminesG;

import lombok.Data;

import java.util.List;

@Data
public class ExaminesGDTO {
    private com.cops.scada.entity.DTO.ExaminesE.SiteInfo SiteInfo;
    private List<SiteDatasG> SiteDatas;
}
