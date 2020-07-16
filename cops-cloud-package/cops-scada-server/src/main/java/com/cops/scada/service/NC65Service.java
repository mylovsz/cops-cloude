package com.cops.scada.service;

import com.cops.entity.nc65.ProductionOrder;

public interface NC65Service {
    ProductionOrder getProductionOrderByNcSn(String ncSn);
}
