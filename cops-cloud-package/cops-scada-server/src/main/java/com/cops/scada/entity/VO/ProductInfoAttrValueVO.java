package com.cops.scada.entity.VO;

import com.cops.scada.entity.ProductInfoAttrValue;
import lombok.Data;

import java.util.List;

@Data
public class ProductInfoAttrValueVO {

    private Long id;

    private List<ProductInfoAttrValue> productInfoAttrValue;
}
