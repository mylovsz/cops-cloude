package com.cops.scada.service;

import com.cops.entity.nc65.DTO.WrapperDTO;
import com.cops.library.until.NC65RestResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "cops-nc65-server")
public interface NC65BatchMaterialService {
    @RequestMapping(value = "/batchMaterial/list", method = RequestMethod.POST)
    NC65RestResponse getBatchMaterial(WrapperDTO wrapperDTO);
}
