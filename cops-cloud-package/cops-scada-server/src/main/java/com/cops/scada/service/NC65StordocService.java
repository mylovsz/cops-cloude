package com.cops.scada.service;

import com.cops.entity.nc65.Stordoc;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "cops-nc65-server")
public interface NC65StordocService {

    @RequestMapping(value = "/stordoc/getStordocs", method = RequestMethod.POST)
    List<Stordoc> getStordocs(Stordoc stordoc);
}
