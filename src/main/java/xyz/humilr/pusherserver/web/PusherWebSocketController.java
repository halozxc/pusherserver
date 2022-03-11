package xyz.humilr.pusherserver.web;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.RestController;
import xyz.humilr.pusherserver.config.JwtProperties;

import xyz.humilr.pusherserver.service.AuthService;
import xyz.humilr.pusherserver.service.UserService;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/socket/{username}/{password}")
@Slf4j
@RestController

public class PusherWebSocketController {


    @Autowired
    AuthService authService;
    /**
     * 存放所有在线的客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(@PathParam("username") String username ,@PathParam("password")String password, Session session) {
      log.info("有新的客户端连接了: {},username is {},user password is{}", session.getId(),username,password);
       if(authService == null){
           log.error("authservice is null");
           return;
       }
        String token = authService.authentication("siri1", "123456");
        if (StringUtils.isBlank(token)) {
           log.info("nothing");
        }
      //将新用户存入在线的组
        clients.put(session.getId(), session);
    }

    /**
     * 客户端关闭
     * @param session session
     */
    @OnClose
    public void onClose(Session session) {
        log.info("有用户断开了, id为:{}", session.getId());
        //将掉线的用户移除在线的组里
        clients.remove(session.getId());
    }

    /**
     * 发生错误
     * @param throwable e
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 收到客户端发来消息
     * @param message  消息对象
     */
    @OnMessage
    public void onMessage(String message,Session session) {

        log.info("服务端收到客户端发来的消息: {},{}", message,session.getId());
        this.sendAll(message);
    }

    /**
     * 群发消息
     * @param message 消息内容
     */
    private void sendAll(String message) {
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            sessionEntry.getValue().getAsyncRemote().sendText(message);
        }
    }
}
