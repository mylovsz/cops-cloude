package com.cops.scada.entity.VO;

import com.cops.scada.base.DataEntity;
import lombok.Data;

@Data
public class SiteStasVO extends DataEntity<StatisticsDayVO> {
    private String value1;
    private String value2;
    private String value3;
    private String value4;
    private String value5;
    private String value6;
}
