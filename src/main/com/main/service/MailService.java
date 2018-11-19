package com.main.service;

import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * @Author: yunxiang.yang
 * @Date: 2018/11/14 18:10
 */

@Service
public class MailService {

    @Autowired
    JavaMailSender mailSender;

    public void sendSimpleEmail(String from, String to, String msg) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject("New Message from " + from);
            simpleMailMessage.setText(msg);
            mailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAttachment(String from, String to, String msg) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);//No set from cause error
            helper.setTo(to);
            helper.setSubject("New Message from " + from);
            helper.setText(msg);
            FileSystemResource couponImage = new FileSystemResource("/collateral/coupon.png");
            helper.addAttachment("Coupon.png", couponImage);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendHTMLEmail(String from, String to, String msg) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("New Message from " + from);
            msg = "<html><body><img src='cid:logo'><h4> New Message from " + from + "</h4></body></html>";
            helper.setText(msg, true);
            ClassPathResource image = new ClassPathResource("logo.png");
            helper.addInline("logo", image);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String renderMsgTemplate(String message, Map<String, String> params) throws Exception {
        SimpleTemplateEngine engine = new SimpleTemplateEngine();
        Template template = null;
        try {
            template = engine.createTemplate(message);
            return template.make(params).toString();
        } catch (Exception e) {
            throw new Exception("渲染短信模版失败:" + e.getMessage());
        }
    }


}
