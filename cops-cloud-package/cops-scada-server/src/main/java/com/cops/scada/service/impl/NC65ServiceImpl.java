package com.cops.scada.service.impl;

import com.cops.entity.nc65.DTO.WrapperDTO;
import com.cops.entity.nc65.ProductionOrder;
import com.cops.library.until.DataConvert;
import com.cops.library.until.NC65RestResponse;
import com.cops.scada.entity.Product;
import com.cops.scada.service.NC65ProductionOrderService;
import com.cops.scada.service.NC65Service;
import com.cops.scada.util.LayerData;
import com.cops.scada.util.RestResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NC65ServiceImpl implements NC65Service {

    @Autowired
    NC65ProductionOrderService nc65ProductionOrderService;

    @Override
    public ProductionOrder getProductionOrderByNcSn(String ncSn) {
        if (StringUtils.isBlank(ncSn)) {
            return null;
        }

        ProductionOrder productionOrder1 = null;

        WrapperDTO wrapperDTO = new WrapperDTO();
        wrapperDTO.setPage(1);
        wrapperDTO.setLimit(1);
        ProductionOrder productionOrder = new ProductionOrder();
        productionOrder.setPmoBillCode(ncSn);
        wrapperDTO.setWrapper(productionOrder);
        NC65RestResponse nc65RestResponse = nc65ProductionOrderService.getProductionOrders(wrapperDTO);
        if (nc65RestResponse != null && nc65RestResponse.getData() != null) {
            LayerData<ProductionOrder> productionOrderPage = (LayerData<ProductionOrder>) DataConvert.mapToObject((Map<String, Object>) nc65RestResponse.getData(), LayerData.class);
            if (productionOrderPage.getCount() > 0) {
                List<ProductionOrder> list = productionOrderPage.getData();
                productionOrder1 = (ProductionOrder) DataConvert.mapToObject((Map<String, Object>) list.get(0), ProductionOrder.class);
                return productionOrder1;
            }
        }

        return null;
    }
}
