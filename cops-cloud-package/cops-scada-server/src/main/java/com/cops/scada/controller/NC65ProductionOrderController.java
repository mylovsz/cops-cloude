package com.cops.scada.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.entity.nc65.DTO.WrapperDTO;
import com.cops.entity.nc65.ProductionOrder;
import com.cops.library.until.DataConvert;
import com.cops.library.until.NC65RestResponse;
import com.cops.scada.annotation.SysLog;
import com.cops.scada.entity.Product;
import com.cops.scada.entity.VO.PlanProductVO;
import com.cops.scada.service.NC65ProductionOrderService;
import com.cops.scada.service.ProductService;
import com.cops.scada.util.LayerData;
import com.cops.scada.util.RestResponse;
import com.xiaoleilu.hutool.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/productionOrder")
public class NC65ProductionOrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NC65ProductionOrderController.class);

    @Autowired
    NC65ProductionOrderService nc65ProductionOrderService;

    @Autowired
    ProductService productService;

    @PostMapping("getProductionOrder")
    @ResponseBody
    public RestResponse getProductionOrder(@RequestParam("id") String id) {
        if (StringUtils.isBlank(id)) {
            return RestResponse.failure("NC单号不能为空");
        }

        ProductionOrder productionOrder1 = null;

        WrapperDTO wrapperDTO = new WrapperDTO();
        wrapperDTO.setPage(1);
        wrapperDTO.setLimit(1);
        ProductionOrder productionOrder = new ProductionOrder();
        productionOrder.setPmoBillCode(id);
        wrapperDTO.setWrapper(productionOrder);
        NC65RestResponse nc65RestResponse = nc65ProductionOrderService.getProductionOrders(wrapperDTO);
        if (nc65RestResponse != null && nc65RestResponse.getData() != null) {
            LayerData<ProductionOrder> productionOrderPage = (LayerData<ProductionOrder>) DataConvert.mapToObject((Map<String, Object>) nc65RestResponse.getData(), LayerData.class);
            if (productionOrderPage.getCount() > 0) {
                List<ProductionOrder> list = productionOrderPage.getData();
                productionOrder1 = (ProductionOrder) DataConvert.mapToObject((Map<String, Object>) list.get(0), ProductionOrder.class);
                Product product = productService.getOneBySn(productionOrder1.getMaterialCode());
                if (product != null) {
                    productionOrder1.setMaterialCode(product.getId() + "");
                }
                else {
                    // 自动生成对应的型号
                    Product product1 = new Product();
                    product1.setSn(productionOrder1.getMaterialCode());
                    product1.setBom(productionOrder1.getMaterialCode());
                    product1.setIntroduction(productionOrder1.getMaterialCode());
                    product1.setName(productionOrder1.getMaterialCode());
                    product1.setState(0); // 在售
                    switch (productionOrder1.getMaterialCode().substring(0, 2)) {
                        case "LA":
                            product1.setType(3);
                            break;
                        case "LC":
                            product1.setType(1);
                            break;
                        case "LD":
                            product1.setType(2);
                            break;
                        case "LF":
                            product1.setType(4);
                            break;
                        case "LG":
                            product1.setType(5);
                            break;
                        case "LM":
                            product1.setType(6);
                            break;
                        case "LP":
                            product1.setType(7);
                            break;
                        case "LR":
                            product1.setType(8);
                            break;
                        case "LS":
                            product1.setType(9);
                            break;
                        case "LT":
                            product1.setType(0);
                            break;
                        default:
                            product1.setType(9);
                            break;
                    }
                    product1 = productService.saveProduct(product1);
                    if (product1 != null) {
                        productionOrder1.setMaterialCode("new:" + product1.getId() + ":"+product1.getSn());
                    } else {
                        productionOrder1.setMaterialCode("0");
                    }
                }
            }
        }

        if (productionOrder1 != null) {
            return RestResponse.success().setData(productionOrder1);
        } else {
            return RestResponse.failure("不存在该设备");
        }
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求NC计划列表数据")
    public LayerData<ProductionOrder> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                         ServletRequest request) {
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<PlanProductVO> layerData = new LayerData<>();
        EntityWrapper<PlanProductVO> wrapper = new EntityWrapper<>();

        ProductionOrder productionOrder1 = null;

        WrapperDTO wrapperDTO = new WrapperDTO();
        wrapperDTO.setPage(page);
        wrapperDTO.setLimit(limit);
        ProductionOrder productionOrder = new ProductionOrder();

        if (!map.isEmpty()) {
            String materialCode = (String) map.get("materialCode");
            if (StringUtils.isNotBlank(materialCode)) {
                productionOrder.setMaterialCode(materialCode.trim());
            }

            String materialName = (String) map.get("materialName");
            if (StringUtils.isNotBlank(materialName)) {
                productionOrder.setMaterialName(materialName.trim());
            }

            String pmoBillCode = (String) map.get("pmoBillCode");
            if (StringUtils.isNotBlank(pmoBillCode)) {
                productionOrder.setPmoBillCode(pmoBillCode.trim());
            }

            String pmoBillCodeOK = (String) map.get("pmoBillCodeOK");
            if (StringUtils.isNotBlank(pmoBillCodeOK)) {
                productionOrder.setPmoBillStatus("1");
                productionOrder.setPmoBillCode(pmoBillCodeOK.trim());
            }

            String batchCode = (String) map.get("batchCode");
            if (StringUtils.isNotBlank(batchCode)) {
                productionOrder.setBatchCode(batchCode.trim());
            }
        }

        wrapperDTO.setWrapper(productionOrder);
        NC65RestResponse nc65RestResponse = nc65ProductionOrderService.getProductionOrders(wrapperDTO);
        if (nc65RestResponse != null && nc65RestResponse.getData() != null) {
            LayerData<ProductionOrder> productionOrderPage = (LayerData<ProductionOrder>) DataConvert.mapToObject((Map<String, Object>) nc65RestResponse.getData(), LayerData.class);
            return productionOrderPage;
        }

        return new LayerData<>();
    }
}
