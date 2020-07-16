package com.cops.entity.nc65.DTO;

import lombok.Data;

/**
 * 通用条件
 */
@Data
public class WrapperDTO {
    private Integer page;
    private Integer limit;
    private Object wrapper;
}
