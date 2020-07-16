package com.cops.scada.service;

import com.baomidou.mybatisplus.service.IService;
import com.cops.entity.nc65.DTO.SaleOrderPageWrapperDTO;
import com.cops.entity.nc65.SaleOrder;
import com.cops.library.until.NC65RestResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "cops-nc65-server")
public interface NC65SaleOrderService {

    @RequestMapping(value = "/saleOrder/list", method = RequestMethod.POST)
    NC65RestResponse getSaleOrder(SaleOrderPageWrapperDTO saleOrderPageWrapperDTO);
}
