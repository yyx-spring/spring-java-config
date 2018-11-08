package com.main.listener;

/**
 * @Author: yunxiang.yang
 * @Date: 2018/11/8 17:05
 */

import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class QueueMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
//        TextMessage tm = (TextMessage) message;
        try {
            ObjectMessage receivedMessage = (ObjectMessage) message;
//            return receivedMessage.getObject();
            System.out.println("QueueMessageListener监听到了文本消息：\t" + receivedMessage.getObject());
            //do something ...
            //回复ACK
//            tm.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
