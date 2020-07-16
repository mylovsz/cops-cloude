package com.cops.entity.nc65.DTO;

import lombok.Data;

import java.util.List;

@Data
public class BomOnhandWrapperDTO {
    List<MaterialCodeWrapperDTO> materialCodeWrapperDTO;
    List<String> stordocCodes;

    /**
     * 是否末级查询
     */
    boolean isAllTtem;

    /**
     * 是否报价
     */
    boolean isPrice;
}
