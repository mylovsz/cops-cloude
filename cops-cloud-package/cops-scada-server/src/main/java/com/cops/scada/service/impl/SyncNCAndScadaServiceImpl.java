package com.cops.scada.service.impl;

import com.cops.entity.nc65.DTO.WrapperDTO;
import com.cops.entity.nc65.ProductionOrder;
import com.cops.library.until.DataConvert;
import com.cops.library.until.NC65DataConverter;
import com.cops.library.until.NC65RestResponse;
import com.cops.scada.entity.Plan;
import com.cops.scada.service.NC65ProductionOrderService;
import com.cops.scada.service.SyncNCAndScadaService;
import com.cops.scada.util.LayerData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SyncNCAndScadaServiceImpl implements SyncNCAndScadaService {

    @Autowired
    NC65ProductionOrderService nc65ProductionOrderService;

    @Override
    public List<ProductionOrder> getNCProductionOrderByPlan(Plan plan) {
        String pmoBillCode;

        if(StringUtils.isNotBlank(plan.getNcId())){
            pmoBillCode = plan.getNcId();
        }
        else {
            pmoBillCode = NC65DataConverter.productionOrderSnToId(plan.getSn());
        }

        if(StringUtils.isNotBlank(pmoBillCode)) {
            WrapperDTO wrapperDTO = new WrapperDTO();
            wrapperDTO.setPage(1);
            wrapperDTO.setLimit(1);
            ProductionOrder productionOrder = new ProductionOrder();
            productionOrder.setPmoBillCode(pmoBillCode);
            //productionOrder.setMaterialName("HID");
            wrapperDTO.setWrapper(productionOrder);
            NC65RestResponse nc65RestResponse = nc65ProductionOrderService.getProductionOrders(wrapperDTO);
            if(nc65RestResponse != null && nc65RestResponse.getData() != null){
                LayerData<ProductionOrder> productionOrderPage = (LayerData<ProductionOrder>) DataConvert.mapToObject((Map<String, Object>) nc65RestResponse.getData(), LayerData.class);
                if(productionOrderPage.getCount() > 0){
                    List<ProductionOrder> list = productionOrderPage.getData();
                    return list;
                }
            }
        }
        return null;
    }
}
