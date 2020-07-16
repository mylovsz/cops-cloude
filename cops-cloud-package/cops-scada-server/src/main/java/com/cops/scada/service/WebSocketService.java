package com.cops.scada.service;


import com.cops.scada.entity.Device;
import com.cops.scada.entity.VO.Result;
import com.cops.scada.entity.WebsocketMessage;
import com.cops.scada.util.RestResponse;

public interface WebSocketService  {
    /**
     * 处理websocket的消息
     * @param message
     * @param deviceId
     * @return
     */
    RestResponse doWebsocketData(String message, String deviceId);

    /**
     * 处理websocket的消息
     * @param websocketMessage
     * @param deviceId
     * @return
     */
    RestResponse doWebsocketData(WebsocketMessage websocketMessage, String deviceId);

    Device checkDevice(String deviceId);

    void updateDeviceState(Device device);
}
