package com.cops.scada.controller.socket;

import com.alibaba.fastjson.JSON;
import com.cops.scada.entity.Device;
import com.cops.scada.service.WebSocketService;
import com.cops.scada.util.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Slf4j
@ServerEndpoint("/websocket/{deviceId}")
public class WebSocket {

    private final static Logger logger = LoggerFactory.getLogger(WebSocket.class);

    @Resource
    SecurityManager securityManager;

    /**
     * 所有实例共用一个 websocket 服务
     */
    private static WebSocketService websocketService;

    private String deviceId;

    private Device device;

    @PostConstruct
    public void init() {
        System.out.println("websocket 加载");
        ThreadContext.bind(securityManager);
    }

    private Session session;
    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();
    private static Map<String, Session> sessionPool = new HashMap<String, Session>();

    @Autowired
    public void setWebsocketService(WebSocketService service) {
        WebSocket.websocketService = service;
        System.out.println("websocket 初始化");
    }


    @OnOpen
    public void onOpen(Session session, @PathParam(value = "deviceId") String deviceId) {
        try {
            this.session = session;
            this.deviceId = deviceId;
            device = websocketService.checkDevice(deviceId);

            // 加入全局列表中
            webSockets.add(this);
            // 加入到用户与 session 的map中
            sessionPool.put(deviceId, session);
            log.info("【websocket消息】有新的连接，总数为:" + webSockets.size());

            if (device == null) {
                RestResponse response = RestResponse.success();
                response.put("type", "register");
                sendOneMessage(deviceId, JSON.toJSONString(response));
            }
        } catch (Exception e) {
        }
    }

    @OnClose
    public void onClose() {
        try {
            if (device == null) {
                device = websocketService.checkDevice(deviceId);
                if (device != null) {
                    device.setState(6);
                    websocketService.updateDeviceState(device);
                }
            }
            webSockets.remove(this);
            log.info("【websocket消息】连接断开，总数为:" + webSockets.size());
        } catch (Exception e) {
        }
    }

    /**
     * 接受前端的消息
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("【websocket消息】收到客户端消息:" + message);
        if (websocketService != null) {
            RestResponse r = websocketService.doWebsocketData(message, deviceId);
            this.session.getAsyncRemote().sendText(JSON.toJSONString(r));
        }
    }

    // 此为广播消息
    public static void sendAllMessage(String message) {
        log.info("【websocket消息】广播消息:" + message);
        for (WebSocket webSocket : webSockets) {
            try {
                if (webSocket.session.isOpen()) {
                    webSocket.session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息
    public static void sendOneMessage(String deviceId, String message) {
        if (StringUtils.isBlank(deviceId)) {
            return;
        }
        Session session = sessionPool.get(deviceId);
        if (session != null && session.isOpen()) {
            try {
                log.info("【websocket消息】 单点消息:" + message);
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息(多人)
    public static void sendMoreMessage(String[] deviceIds, String message) {
        for (String deviceId : deviceIds) {
            Session session = sessionPool.get(deviceId);
            if (session != null && session.isOpen()) {
                try {
                    log.info("【websocket消息】 单点消息:" + message);
                    session.getAsyncRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
