package com.cops.scada.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.entity.nc65.DTO.SaleOrderPageWrapperDTO;
import com.cops.entity.nc65.SaleOrder;
import com.cops.library.until.DataConvert;
import com.cops.library.until.NC65RestResponse;
import com.cops.scada.annotation.SysLog;
import com.cops.scada.service.NC65SaleOrderService;
import com.cops.scada.util.LayerData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/saleOrder")
public class NC65SaleOrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NC65SaleOrderController.class);

    @Autowired
    NC65SaleOrderService nc65SaleOrderService;


    @GetMapping("list")
    @SysLog("跳转订单管理页面列表")
    public String list(){
        return "/saleOrder/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求订单管理页面列表数据")
    public LayerData<SaleOrder> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                     @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                     ServletRequest request){

        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<SaleOrder> layerData = new LayerData<>();
        EntityWrapper<SaleOrder> wrapper = new EntityWrapper<>();

        SaleOrderPageWrapperDTO saleOrderPageWrapperDTO = new SaleOrderPageWrapperDTO();

        SaleOrder saleOrder =null;
        if (map!=null) {
            saleOrder = new SaleOrder();
            //saleOrder.setSaleOrderId((String) map.get("sale_order_id"));
            saleOrder.setBillMaker((String) map.get("bill_maker"));
            saleOrder.setStatus((String) map.get("status"));
            saleOrder.setPi((String) map.get("pi"));
        }

        saleOrderPageWrapperDTO.setSaleOrder(saleOrder);

        saleOrderPageWrapperDTO.setPage(page);
        saleOrderPageWrapperDTO.setLimit(limit);

        NC65RestResponse nc65RestResponse = nc65SaleOrderService.getSaleOrder(saleOrderPageWrapperDTO);//存在超时问题
        LayerData<SaleOrder> SaleOrders = (LayerData<SaleOrder>) DataConvert.mapToObject((Map<String, Object>) nc65RestResponse.getData(), LayerData.class);
        return SaleOrders;
    }
}
