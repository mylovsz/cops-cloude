package com.cops.nc65.Controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.entity.nc65.DTO.SaleOrderPageWrapperDTO;
import com.cops.library.until.LayerData;
import com.cops.library.until.NC65RestResponse;
import com.cops.entity.nc65.SaleOrder;
import com.cops.nc65.service.SaleOrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saleOrder")
public class SaleOrderController {

    @Autowired
    private SaleOrderService saleOrderService;

    @ApiOperation(value = "获取销售订单信息", notes = "当前仅支持《制单人》《主键》《状态》查询")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "SaleOrderPageWrapperDTO", name = "saleOrderPageWrapperDTO", value = "请求参数", required = true)
    })
    @PostMapping("list")
    @ResponseBody
    public NC65RestResponse list(@RequestBody SaleOrderPageWrapperDTO saleOrderPageWrapperDTO){
        SaleOrder saleOrder = saleOrderPageWrapperDTO.getSaleOrder();
        Integer page = saleOrderPageWrapperDTO.getPage();
        Integer limit = saleOrderPageWrapperDTO.getLimit();
        EntityWrapper<SaleOrder> wrapper = new EntityWrapper<>();
        wrapper.eq("ss_saleorder.dr", 0);
        wrapper.eq("approver_user.dr", 0);
        wrapper.eq("billmaker_user.dr", 0);
        wrapper.eq("cemployeeid_psndoc.dr", 0);
        wrapper.eq("ccustomer_customer.dr", 0);
        if(saleOrder != null){
            if(StringUtils.isNotBlank(saleOrder.getSaleOrderId())){
                // 主键
                wrapper.eq("ss_saleorder.csaleorderid", saleOrder.getSaleOrderId());
            }
            if(StringUtils.isNotBlank(saleOrder.getBillMaker())){
                // 制单人
                wrapper.eq("approver_user.user_name", saleOrder.getBillMaker());
            }
            if(StringUtils.isNotBlank(saleOrder.getStatus())){
                // 状态（）
                wrapper.eq("ss_saleorder.fstatusflag", saleOrder.getStatus());
            }
            if(StringUtils.isNotBlank(saleOrder.getPi())){
                // 状态（）
                wrapper.like("ss_saleorder.vdef2", saleOrder.getPi());
            }
        }
        Page<SaleOrder> saleOrders = saleOrderService.getPage(new Page(page, limit), wrapper);

        LayerData<SaleOrder> layerData = new LayerData<>();
        layerData.setData(saleOrders.getRecords());
        layerData.setCount(saleOrders.getTotal());

        return NC65RestResponse.success().setData(layerData);
    }
}
