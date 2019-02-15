package com.judy.demo.ServiceIml;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: judy
 * @Description:
 * @Date: Created in 15:58 2019/2/10
 */

@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocketIml {
    private static CopyOnWriteArraySet<WebSocketIml> webSocketImlCopyOnWriteArraySet = new CopyOnWriteArraySet<>();
    private Session session;

    /**
     * 有新的连接
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
      this.session=session;
      webSocketImlCopyOnWriteArraySet.add(this);
      log.info("[websocket消息有新的连接,总数-{}]",webSocketImlCopyOnWriteArraySet.size());
    }

    /**
     * 关闭
     */
    @OnClose
    public void onClose(){
        webSocketImlCopyOnWriteArraySet.remove(this);
        log.info("[websocket关闭连接,总数-{}]",webSocketImlCopyOnWriteArraySet.size());
    }

    @OnMessage
    public void onMessage(String message){
        log.info("[websocket收到客户端发来的消息,总数-{}]",webSocketImlCopyOnWriteArraySet.size());
    }

    public void sendMessage(String message){
        log.info("[websocket广播消息,message-{}]",message);
    }
}
