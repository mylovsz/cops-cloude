package com.cops.scada.service;

import com.cops.entity.nc65.DTO.SaleOrderDetailPageWrapperDTO;
import com.cops.library.until.NC65RestResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "cops-nc65-server")
public interface NC65SaleOrderDetailService {
    @RequestMapping(value = "/saleOrderDetail/list", method = RequestMethod.POST)
    NC65RestResponse getSaleOrderDetail(SaleOrderDetailPageWrapperDTO saleOrderDetailPageWrapperDTO);
}
