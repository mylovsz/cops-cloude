package com.cops.scada.entity.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ExaminesTestTime {
    private Long id;
    private String productSn;
    private String planSn;
    private String produceSn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date collectDate;
    private Long testTime;
}
