package com.cops.scada.entity.VO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.TreeEntity;
import com.cops.scada.entity.ProductInfoAttrValue;
import com.cops.scada.entity.SeriesAttr;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 属性
 * </p>
 *
 * @author wanglm
 * @since 2020-04-21
 */
@Data
public class SeriesAttrAndValueVO {
	/**
	 * 查询条件
	 */
	private List<ProductInfoAttrValue> condition;

	/**
	 * 系列属性
	 */
	private SeriesAttr seriesAttr;
}
