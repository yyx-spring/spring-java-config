package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * @Author: yunxiang.yang
 * @Date: 2018/11/7 15:09
 */

@Service
public class JmsService {

    @Autowired
    private JmsOperations jmsOperations;


    public void send(final String message) {
        jmsOperations.send("xiangzi.queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(message);
            }
        });
    }

    public void convertAndSend(Object object) {
        jmsOperations.convertAndSend(object);
    }

    public Object receive() {
        try {
            ObjectMessage receivedMessage = (ObjectMessage) jmsOperations.receive();
            return receivedMessage.getObject();
        } catch (JMSException jmsException) {
            //将javax.jms.JMSException转为org.springframework.jms.JmsException
            throw JmsUtils.convertJmsAccessException(jmsException);
        }
    }

    public Object receiveAndConvert() {
        Object object = (Object) jmsOperations.receiveAndConvert();
        return object;
    }
}
