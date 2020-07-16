package com.cops.entity.scada.VO;

import com.cops.entity.nc65.DTO.MaterialCodeWrapperDTO;
import lombok.Data;

import java.util.List;

@Data
public class BomOnhandWrapperVO {
    Boolean searchState;
    Boolean isAllItem;
    List<MaterialCodeWrapperDTO> materialCodeWrapperDTOS;
    List<String> stordocs;

    /**
     * 是否报价
     */
    Boolean isPrice;
}
