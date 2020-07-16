package com.cops.scada.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(value="控制命令", description="控制命令")
public class WebsocketMessage {

    /**
     * 消息类型
     */
    public static String MSG_CATEGORY = "3";

    /**
     * 发布人
     */
    @ApiModelProperty(hidden = true)
    private String sender;

    /**
     * 协议类型
     */
    @ApiModelProperty(value = "协议类型", required = true)
    private String type;

    /**
     * 返回类型的文本，当前用于数据库title
     * @return
     */
    @ApiModelProperty(hidden = true)
    public String getTypeText() {
        // todo [增加协议] 2. 增加 websocket 请求类型的文字字符串
        switch (type){
            case "LEDLAOHUA":
                return "LED 老化";
            default:
                return "其他协议";
        }
    }

    /**
     * 控制对象编号
     */
    @ApiModelProperty(value = "控制对象编号", required = true)
    private String targetSn;

    /**
     * 控制对象名称
     */
    @ApiModelProperty(value = "控制对象名称")
    private String targetName;

    /**
     * 消息实体
     */
    @ApiModelProperty(value = "消息实体")
    private Object value;

    /**
     * 消息内容
     */
    @ApiModelProperty(value = "附加消息")
    private Object content;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(hidden = true)
    private Date createDate;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "接收人（自己的ID）", required = true)
    private String userId;
}

