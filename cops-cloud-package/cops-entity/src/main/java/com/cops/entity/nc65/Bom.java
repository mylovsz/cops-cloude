package com.cops.entity.nc65;

import lombok.Data;

import java.util.List;

@Data
public class Bom {
    String bomId;
    String billMaker;
    String approverUser;
    String kititem;
    Integer bomStatus;
    Integer bomType;
    String ecnId;
    String materialId;
    String materialCode;
    String materialName;
    String materialSpec;
    String bomSource;
    String versionType;
    String version;
    String billCode;
    /**
     * 是否默认bom（ Y 或者 N ）
     */
    String bomDefault;
}
