package com.main.websocket;

/**
 * @Author: yunxiang.yang
 * @Date: 2018/11/12 11:08
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class MarcoHandler extends AbstractWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(MarcoHandler.class);

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("Received message: " + message.getPayload());

        Thread.sleep(2000);

        session.sendMessage(new TextMessage("Polo!"));
    }
}

