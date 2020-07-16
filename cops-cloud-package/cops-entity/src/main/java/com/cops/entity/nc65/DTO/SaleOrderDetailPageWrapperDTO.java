package com.cops.entity.nc65.DTO;

import com.cops.entity.nc65.SaleOrderDetail;
import lombok.Data;

@Data
public class SaleOrderDetailPageWrapperDTO {
    private Integer page;
    private Integer limit;
    private SaleOrderDetail saleOrderDetail;
}
