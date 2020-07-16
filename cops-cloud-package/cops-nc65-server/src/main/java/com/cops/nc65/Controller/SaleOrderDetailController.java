package com.cops.nc65.Controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.entity.nc65.DTO.SaleOrderDetailPageWrapperDTO;
import com.cops.library.until.LayerData;
import com.cops.library.until.NC65RestResponse;
import com.cops.entity.nc65.SaleOrderDetail;
import com.cops.nc65.service.SaleOrderDetailService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saleOrderDetail")
public class SaleOrderDetailController {

    @Autowired
    private SaleOrderDetailService saleOrderDetailService;

    @ApiOperation(value = "获取销售订单详情信息", notes = "当前仅支持《主键》查询")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "SaleOrderDetailPageWrapperDTO", name = "saleOrderDetailPageWrapperDTO", value = "请求参数", required = true)
    })
    @PostMapping("list")
    @ResponseBody
    public NC65RestResponse list(@RequestBody SaleOrderDetailPageWrapperDTO saleOrderDetailPageWrapperDTO){

        SaleOrderDetail saleOrderDetail = saleOrderDetailPageWrapperDTO.getSaleOrderDetail();
/*        Integer page = saleOrderDetailPageWrapperDTO.getPage();
        Integer limit = saleOrderDetailPageWrapperDTO.getLimit();*/
        EntityWrapper<SaleOrderDetail> wrapper = new EntityWrapper<>();
        wrapper.eq("ssb_saleorder.dr", 0);
        wrapper.eq("bm_material.dr", 0);

        if(saleOrderDetail != null){
            if(StringUtils.isNotBlank(saleOrderDetail.getMaterialId())){
                // 主键
                wrapper.eq("ssb_saleorder.csaleorderid", saleOrderDetail.getMaterialId());
            }
        }
        List<SaleOrderDetail> saleOrderDetailPage = saleOrderDetailService.getSaleOrderDetails(wrapper);
        return NC65RestResponse.success().setData(saleOrderDetailPage);
//        List<SaleOrderDetail> saleOrderDetails = saleOrderDetailService.getSaleOrderDetailList();
//        return NC65RestResponse.success().setData(saleOrderDetails);
    }
}
