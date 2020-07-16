package com.cops.entity.nc65.DTO;

import com.cops.entity.nc65.SaleOrder;
import lombok.Data;

@Data
public class SaleOrderPageWrapperDTO {
    private Integer page;
    private Integer limit;
    private SaleOrder saleOrder;
}
