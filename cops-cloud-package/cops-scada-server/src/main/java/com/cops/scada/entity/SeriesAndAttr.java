package com.cops.scada.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.BaseEntity;
import com.cops.scada.base.TreeEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 属性
 * </p>
 *
 * @author wanglm
 * @since 2020-04-17
 */
@TableName("scada_series_attr")
@Data
public class SeriesAndAttr extends BaseEntity<SeriesAndAttr> {

    private static final long serialVersionUID = 1L;

    /**
     * 属性id
     */
	@TableField("series_attr_id")
	private Long seriesAttrId;
    /**
     * 关联系列id
     */
	@TableField("series_id")
	private Long seriesId;

	/**
	 * 排序字段
	 */
	@Getter
	@Setter
	private Long sort;


	@Override
	public String toString() {
		return "SeriesAndAttr{" +
			", seriesAttrId=" + seriesAttrId +
			", seriesId=" + seriesId +
			"}";
	}
}
