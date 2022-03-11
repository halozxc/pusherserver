package xyz.humilr.pusherserver.web;

import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;


import xyz.humilr.pusherserver.dao.UserMapper;
import xyz.humilr.pusherserver.pojo.module.Message;
import xyz.humilr.pusherserver.pojo.module.User;
import xyz.humilr.pusherserver.service.AuthService;
import xyz.humilr.pusherserver.service.UserService;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/socket/{username}/{password}")
@Slf4j
@Component

public class PusherWebSocketController {

    static UserMapper userMapper;
    static UserService userService;
    static AuthService authService;
    /**
     * 存放所有在线的客户端
     */
    private static Map<Integer, Session> clients = new ConcurrentHashMap<>();
    @Autowired
    public  void setUserMapper(UserMapper userMapper){
        PusherWebSocketController.userMapper = userMapper;
    }
    @Autowired
    public void setUserService(UserService userService){
        PusherWebSocketController.userService = userService;
    }
    @Autowired
    public void setAuthService(AuthService authService){
        PusherWebSocketController.authService = authService;
    }


    @OnOpen
    public void onOpen(@PathParam("username") String username ,@PathParam("password")String password, Session session) {
      log.info("有新的客户端连接了: {},username is {},user password is{}", session.getId(),username,password);

        String token = authService.authentication("siri1", "123456");
      if(token != null){
          User user = userService.queryUser(username, password);
          clients.put(user.getId(), session);
          log.info("注册用户已加入连接，id为{}",user.getId());
      }

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
        for (Map.Entry<Integer, Session> sessionEntry : clients.entrySet()) {
            sessionEntry.getValue().getAsyncRemote().sendText(message);
        }
    }
    /**
     *
     * 向指定用户发送信息
     */
    static public boolean sendMessage(Integer id, String message){
        Session target  = clients.get(id);
        if(target!=null){
            clients.get(id).getAsyncRemote().sendText(message);
            return true;
        }else {
            log.info("user: {} disconect",id);
             return false;
        }

    }
}
