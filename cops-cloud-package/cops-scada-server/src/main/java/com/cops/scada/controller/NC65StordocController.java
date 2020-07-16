package com.cops.scada.controller;

import com.cops.entity.nc65.Stordoc;
import com.cops.scada.base.BaseController;
import com.cops.scada.service.NC65StordocService;
import com.cops.scada.util.LayerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/ncStordoc")
public class NC65StordocController extends BaseController {

    @Autowired
    private NC65StordocService nc65StordocService;

    @PostMapping("getStordocs")
    @ResponseBody
    LayerData<Stordoc> getStordocs(@RequestBody Stordoc stordoc){
        LayerData<Stordoc> layerData = new LayerData<>();
        List<Stordoc> stordocList = nc65StordocService.getStordocs(stordoc);
        layerData.setCount(stordocList.size());
        layerData.setData(stordocList);
        return layerData;
    }
}
