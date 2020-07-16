package com.cops.scada.entity.DTO.ExaminesH;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SiteDatasH {
    /**
     * 产品型号
     */
    private String Product;

    /**
     * 产品编码
     */
    private String Barcode;

    /**
     * 测试结果
     */
    private String Result;

    /**
     * 失败时间
     */
    private String FailTime;

    /**
     * 创建日期
     */
    private Date CreatedDate;
}
