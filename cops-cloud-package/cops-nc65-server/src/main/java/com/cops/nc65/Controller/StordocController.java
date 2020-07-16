package com.cops.nc65.Controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.entity.nc65.Stordoc;
import com.cops.nc65.service.StordocService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stordoc")
public class StordocController {

    @Autowired
    private StordocService stordocService;

    @ApiOperation(value = "获取仓库信息", notes = "当前仅支持查询")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Stordoc", name = "stordoc", value = "请求参数", required = true)
    })
    @PostMapping("getStordocs")
    @ResponseBody
    public List<Stordoc> getStordocs(@RequestBody Stordoc stordoc){
        EntityWrapper<Stordoc> wrapper = new EntityWrapper<>();
        wrapper.eq("stordoc.dr", 0);
        wrapper.eq("oo_orgs.dr", 0);
        wrapper.orderBy("stordoc.code");
        if(stordoc == null)
            return null;
        if(StringUtils.isNotBlank(stordoc.getStordocName())){
            wrapper.eq("stordoc.name", stordoc.getStordocName());
        }
        if(StringUtils.isNotBlank(stordoc.getOrgsCode())){
            wrapper.eq("oo_orgs.code", stordoc.getOrgsCode());
        }
        if(StringUtils.isNotBlank(stordoc.getStordocCode())){
            wrapper.eq("stordoc.code", stordoc.getStordocCode());
        }
        if(StringUtils.isNotBlank(stordoc.getStordocId())){
            wrapper.eq("stordoc.pk_stordoc", stordoc.getStordocId());
        }
        return stordocService.getStordocs(wrapper);
    }
}
