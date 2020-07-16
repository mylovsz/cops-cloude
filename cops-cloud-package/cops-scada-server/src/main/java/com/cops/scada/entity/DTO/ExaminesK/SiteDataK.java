package com.cops.scada.entity.DTO.ExaminesK;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;


@Data
public class SiteDataK {
    private String ProductModel;
    private String JobNumber;
    private String Barcode;

    private String Result;
    private Date DateTime;

    private String TestItem;
    private String ItemName;
    private String USL;
    private String LSL;
    private String Value;
}
