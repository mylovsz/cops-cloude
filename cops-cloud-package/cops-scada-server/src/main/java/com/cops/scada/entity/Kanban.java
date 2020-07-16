package com.cops.scada.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cops.scada.base.DataEntity;

/**
 * <p>
 * 看板配置
 * </p>
 *
 * @author wanglm
 * @since 2020-04-09
 */
@TableName("scada_kanban")
public class Kanban extends DataEntity<Kanban> {

    private static final long serialVersionUID = 1L;

    /**
     * 看板名称
     */
	private String name;
    /**
     * 配置内容 json
     */
	private String content;
    /**
     * 版本
     */
	private String version;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


	@Override
	public String toString() {
		return "Kanban{" +
			", name=" + name +
			", content=" + content +
			", version=" + version +
			"}";
	}
}
