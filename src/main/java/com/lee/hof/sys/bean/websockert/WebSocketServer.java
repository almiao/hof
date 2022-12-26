package com.lee.hof.sys.bean.websockert;

import com.alibaba.fastjson.JSONObject;
import com.lee.hof.sys.bean.model.ChatContent;
import com.lee.hof.sys.mapper.ChatContentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@Service
@ServerEndpoint("/api/websocket/{userId}")
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    private static ConcurrentHashMap<Long, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private Long userId = null;

    private static ApplicationContext applicationContext;

    private  ChatContentMapper chatContentMapper;


    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Long userId) {
        this.session = session;
        webSocketMap.put(userId, this);//加入set中
        this.userId = userId;
        addOnlineCount();           //在线数加1
        log.info("有新窗口开始监听:" + userId + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketMap.remove(this.userId);
        subOnlineCount();           //在线数减1
        //断开连接情况下，更新主板占用情况为释放
        log.info("释放的sid为："+ userId);
        //这里写你 释放的时候，要处理的业务
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());

    }

    public static String ConfirmRead ="CONFIRM_READ";

    /**
     * 收到客户端消息后调用的方法
     * @ Param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {

        ChatContent chatContent = JSONObject.parseObject(message, ChatContent.class);
        Long toUserId =  chatContent.getToUserId();

        WebSocketServer webSocketServer = webSocketMap.get(toUserId);
        if(webSocketServer != null){
            log.info("链接在线");
            chatContent.setIsRead(1);
            webSocketServer.sendMessage(JSONObject.toJSONString(chatContent));
        }else{
            log.info("链接不在线");
            chatContent.setIsRead(0);
        }
        if(chatContentMapper == null) {
            chatContentMapper = applicationContext.getBean(ChatContentMapper.class);
        }
        chatContentMapper.insert(chatContent);
    }

    /**
     * @ Param session
     * @ Param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
//    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
//        log.info("推送消息到窗口" + sid + "，推送内容:" + message);
//
//        for (WebSocketServer item : webSocketSet) {
//            try {
//                //这里可以设定只推送给这个sid的，为null则全部推送
//                if (sid == null) {
////                    item.sendMessage(message);
//                } else if (item.userId.equals(sid)) {
//                    item.sendMessage(message);
//                }
//            } catch (IOException e) {
//                continue;
//            }
//        }
//    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}
