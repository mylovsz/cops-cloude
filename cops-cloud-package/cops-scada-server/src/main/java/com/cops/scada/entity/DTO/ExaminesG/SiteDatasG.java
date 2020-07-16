package com.cops.scada.entity.DTO.ExaminesG;

import lombok.Data;

import java.util.Date;

@Data
public class SiteDatasG {

    private String internalNumber;

    private String externalNumber;

    private Date creatDateTime;

    private Date updateDateTime;
}
