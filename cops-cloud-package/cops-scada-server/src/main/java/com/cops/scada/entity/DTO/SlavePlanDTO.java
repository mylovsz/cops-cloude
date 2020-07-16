package com.cops.scada.entity.DTO;

import com.cops.scada.entity.Plan;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 *
 * @Author: wangermao
 * @Date: 2020-04-18 10:43
 * @Version: V1.0
 */
@Getter
@Setter
public class SlavePlanDTO {

    public String masterPlanId;

    public Plan slavePlan;
}
