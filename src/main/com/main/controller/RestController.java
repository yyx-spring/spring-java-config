package com.main.controller;

import com.main.domain.Bean1;
import com.main.domain.Bean2;
import com.main.service.JmsService;
import com.main.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yunxiang.yang
 * @Date: 2018/10/22 16:20
 */

@Controller
@RequestMapping("/rest")
public class RestController {

    @Autowired
    JmsService jmsService;
    @Autowired
    MailService mailService;


    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Bean1> spittles(@RequestParam long max, @RequestParam(value = "count", defaultValue = "20") int count) {
        List list = new ArrayList();
        Bean1 b1 = new Bean1("zhangsan", 18);
        Bean1 b2 = new Bean1("lisi", 99);
        list.add(b1);
        list.add(b2);
        return list;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Bean1 saveSpittle(@RequestBody Bean1 bean1) {
        return bean1;
    }

    @RequestMapping(value = "/send")
    public @ResponseBody
    String send(HttpServletRequest request) {
        jmsService.send("hello, activeMQ");
        return "Success";
    }

    @RequestMapping(value = "/convertAndSend")
    public @ResponseBody
    String convertAndSend(HttpServletRequest request) {
        Bean2 bean2 = new Bean2();
        jmsService.convertAndSend(bean2);
        return "Success";
    }


    @RequestMapping(value = "/receive")
    public @ResponseBody
    Object receive(HttpServletRequest request) {
        return jmsService.receive();
    }

    @RequestMapping(value = "/receiveAndConvert")
    public @ResponseBody
    Object receiveAndConvert(HttpServletRequest request) {
        return jmsService.receiveAndConvert();
    }

    @RequestMapping("/sendSimpleMail")
    public @ResponseBody
    String sendSimpleEmail() {
        String from = "token@topca.cn";
        String to = "yunxiang.yang@topca.cn";
        String message = "Hello World!";
        mailService.sendSimpleEmail(from, to, message);
        return "Success~";
    }

    @RequestMapping("/sendAttachment")
    public @ResponseBody
    String sendAttachment() {
        String from = "token@topca.cn";
        String to = "yunxiang.yang@topca.cn";
        String message = "Hello World!";
        mailService.sendAttachment(from, to, message);
        return "Success~";
    }


    @RequestMapping("/renderMsgTemplate")
    public @ResponseBody
    String renderMsgTemplate() throws Exception {
        String message = "模板：username--{username},password--{password},age--{age}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", "赵铁柱");
        params.put("passowrd", "142508");
        params.put("age", "98");
        String result = mailService.renderMsgTemplate(message, params);
        return result;
    }
}
