package com.cops.scada.controller;

import com.cops.entity.nc65.DTO.SaleOrderDetailPageWrapperDTO;
import com.cops.entity.nc65.SaleOrderDetail;
import com.cops.library.until.NC65RestResponse;
import com.cops.scada.annotation.SysLog;
import com.cops.scada.service.NC65SaleOrderDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/saleOrderDetail")
public class NC65SaleOrderDetailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NC65SaleOrderController.class);

    @Autowired
    NC65SaleOrderDetailService nc65SaleOrderDetailService;


    @GetMapping("list")
    @SysLog("跳转并请求订单详情列表数据")
    public String list(String id,String billcode, Model model){


        SaleOrderDetailPageWrapperDTO saleOrderDetailPageWrapperDTO = new SaleOrderDetailPageWrapperDTO();
        SaleOrderDetail saleOrderDetail =null;
        if (id!=null) {
            saleOrderDetail = new SaleOrderDetail();
            saleOrderDetail.setMaterialId(id);
        }
        saleOrderDetailPageWrapperDTO.setSaleOrderDetail(saleOrderDetail);

        NC65RestResponse nc65RestResponse = nc65SaleOrderDetailService.getSaleOrderDetail(saleOrderDetailPageWrapperDTO);
        List<SaleOrderDetail> saleOrderDetails=(List<SaleOrderDetail>) nc65RestResponse.getData();
        model.addAttribute("datas", saleOrderDetails);
        model.addAttribute("billcode", billcode);
        return "/saleOrderDetail/list";
    }

 /*   @PostMapping("list")
    @ResponseBody
    @SysLog("请求订单详情列表数据")
    public LayerData<SaleOrderDetail> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                           @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                           ServletRequest request){

        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<SaleOrderDetail> layerData = new LayerData<>();
        EntityWrapper<SaleOrderDetail> wrapper = new EntityWrapper<>();

        SaleOrderDetailPageWrapperDTO saleOrderDetailPageWrapperDTO = new SaleOrderDetailPageWrapperDTO();

        SaleOrderDetail saleOrderDetail =null;
        if (map!=null) {
            saleOrderDetail = new SaleOrderDetail();
            saleOrderDetail.setMaterialId((String) map.get("sale_order_id"));
        }

        saleOrderDetailPageWrapperDTO.setSaleOrderDetail(saleOrderDetail);

        saleOrderDetailPageWrapperDTO.setPage(page);
        saleOrderDetailPageWrapperDTO.setLimit(limit);

        NC65RestResponse nc65RestResponse = nc65SaleOrderDetailService.getSaleOrder(saleOrderDetailPageWrapperDTO);//存在超时问题
        LayerData<SaleOrderDetail> SaleOrders = (LayerData<SaleOrderDetail>) DataConvert.mapToObject((Map<String, Object>) nc65RestResponse.getData(), LayerData.class);
        return SaleOrders;
    }*/
}
