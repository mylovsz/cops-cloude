package com.cops.scada.entity.VO;

import com.xiaoleilu.hutool.date.DateTime;
import lombok.Data;

import java.util.Date;

@Data
public class StatisticsExaminesCollect {
    private Date collectDate;
    private Long collectCount;
}
