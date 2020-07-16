package com.cops.nc65.Controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.entity.nc65.DTO.WrapperDTO;
import com.cops.entity.nc65.ProductionOrder;
import com.cops.library.until.DataConvert;
import com.cops.library.until.LayerData;
import com.cops.library.until.NC65RestResponse;
import com.cops.nc65.service.ProductionOrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/productionOrder")
public class ProductionOrderController {

    @Autowired
    private ProductionOrderService productionOrderService;

    @ApiOperation(value = "获取生产订单信息", notes = "当前仅支持《》《》《》查询")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "WrapperDTO", name = "wrapperDTO", value = "请求参数", required = true)
    })
    @PostMapping("list")
    @ResponseBody
    public NC65RestResponse list(@RequestBody WrapperDTO wrapperDTO){
        ProductionOrder productionOrder = (ProductionOrder) DataConvert.mapToObject((Map<String, Object>) wrapperDTO.getWrapper(), ProductionOrder.class);
        Integer page = wrapperDTO.getPage();
        Integer limit = wrapperDTO.getLimit();
        EntityWrapper<ProductionOrder> wrapper = new EntityWrapper<>();
        wrapper.eq("mmm_mo.dr", 0); // 增加删除标记
//        wrapper.eq("bm_material.dr", 0); // 增加删除标记
//        wrapper.eq("bc_customer.dr", 0); // 增加删除标记
//        wrapper.eq("bb_bom.dr", 0); // 增加删除标记
//        wrapper.eq("mp_pmo.dr", 0); // 增加删除标记
//        wrapper.eq("pp_pb.dr", 0); // 增加删除标记
//        wrapper.eq("od_dept.dr", 0); // 增加删除标记
//        wrapper.eq("bw_wk.dr", 0); // 增加删除标记
//        wrapper.eq("mbp_batch_prod.dr", 0); // 增加删除标记
        if(productionOrder != null){
            if(StringUtils.isNotBlank(productionOrder.getPmoBillCode())){
                // 生产订单号
                wrapper.like("mp_pmo.vbillcode", productionOrder.getPmoBillCode());
            }
            if(StringUtils.isNotBlank(productionOrder.getMaterialName())){
                // 型号
                wrapper.like("bm_material.name", productionOrder.getMaterialName());
            }
            if(StringUtils.isNotBlank(productionOrder.getMaterialCode())){
                // code
                wrapper.like("bm_material.code", productionOrder.getMaterialCode());
            }
            if(StringUtils.isNotBlank(productionOrder.getBatchCode())){
                // 生产批次
                wrapper.like("mmm_mo.vbatchcode", productionOrder.getBatchCode());
            }
            if(StringUtils.isNotBlank(productionOrder.getPmoBillStatus())){
                // 状态
                wrapper.like("mp_pmo.fbillstatus", productionOrder.getPmoBillStatus());
            }
        }
        Page<ProductionOrder> productionOrders = productionOrderService.getPage(new Page(page, limit), wrapper);

        LayerData<ProductionOrder> layerData = new LayerData<>();
        layerData.setData(productionOrders.getRecords());
        layerData.setCount(productionOrders.getTotal());

        return NC65RestResponse.success().setData(layerData);
    }
}
