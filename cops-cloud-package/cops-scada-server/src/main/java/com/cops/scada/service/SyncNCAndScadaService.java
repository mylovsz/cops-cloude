package com.cops.scada.service;

import com.cops.entity.nc65.ProductionOrder;
import com.cops.scada.entity.Plan;

import java.util.List;

public interface SyncNCAndScadaService {

    List<ProductionOrder> getNCProductionOrderByPlan(Plan plan);
}
