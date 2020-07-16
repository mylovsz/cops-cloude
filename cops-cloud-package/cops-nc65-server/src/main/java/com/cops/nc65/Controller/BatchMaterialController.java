package com.cops.nc65.Controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.entity.nc65.BatchMaterial;
import com.cops.entity.nc65.DTO.WrapperDTO;
import com.cops.library.until.DataConvert;
import com.cops.library.until.LayerData;
import com.cops.library.until.NC65RestResponse;
import com.cops.nc65.service.BatchMaterialService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/batchMaterial")
public class BatchMaterialController {
    @Autowired
    private BatchMaterialService batchMaterialService;

    @ApiOperation(value = "获取物料信息", notes = "当前仅支持《》《》《》查询")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "WrapperDTO", name = "wrapperDTO", value = "请求参数", required = true)
    })
    @PostMapping("list")
    @ResponseBody
    public NC65RestResponse list(@RequestBody WrapperDTO wrapperDTO){
        BatchMaterial batchMaterial = (BatchMaterial) DataConvert.mapToObject((Map<String, Object>) wrapperDTO.getWrapper(), BatchMaterial.class);
        Integer page = wrapperDTO.getPage();
        Integer limit = wrapperDTO.getLimit();
        EntityWrapper<BatchMaterial> wrapper = new EntityWrapper<>();
        wrapper.eq("imb_material.dr", 0);
//        wrapper.eq("bm_material.dr", 0);
//        wrapper.eq("sb_batchcode.dr", 0);
//        wrapper.eq("bs_stordoc.dr", 0);
//        wrapper.eq("br_rack.dr", 0);
//        wrapper.eq("bs_supplier.dr", 0);
//        wrapper.eq("oo_orgs.dr", 0);

        if(batchMaterial != null){
            if(StringUtils.isNotBlank(batchMaterial.getPmoBillCode())){
                // 生产订单号
                wrapper.like("imb_material.vfirstbillcode", batchMaterial.getPmoBillCode());
            }
            if(StringUtils.isNotBlank(batchMaterial.getOrgsCode())){
                // 组织
                wrapper.like("oo_orgs.code", batchMaterial.getOrgsCode());
            }
        }
        Page<BatchMaterial> batchMaterialPage = batchMaterialService.getPage(new Page(page, limit), wrapper);

        LayerData<BatchMaterial> layerData = new LayerData<>();
        layerData.setData(batchMaterialPage.getRecords());
        layerData.setCount(batchMaterialPage.getTotal());

        return NC65RestResponse.success().setData(layerData);
    }
}
