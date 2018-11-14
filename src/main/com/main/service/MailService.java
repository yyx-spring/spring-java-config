package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Author: yunxiang.yang
 * @Date: 2018/11/14 18:10
 */

@Service
public class MailService {

    @Autowired
    JavaMailSender mailSender;

    public void sendSimpleEmail(String from, String to, String msg) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("New Message from " + from);
        simpleMailMessage.setText(msg);
        mailSender.send(simpleMailMessage);
    }

}
