package com.main.controller;

import com.main.config.DataSourceConfig;
import com.main.config.RootConfig;
import com.main.config.WebConfig;
import com.main.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
//@Profile("test")
//@Scope(WebApplicationContext.SCOPE_SESSION)
public class ProfileController {
    /*@Autowired
    private IndexService indexService;
*/

    @Autowired
    DataSourceConfig dataSourceConfig;
    @Autowired
    RootConfig rootConfig;
    @Autowired
    WebConfig webConfig;



    @RequestMapping("/test")
    public String index(HttpServletRequest request) {
        throw new MyException("my exception");
//        return "THIS IS A TEST.WELCOME";
    }

    @RequestMapping("/c")
    public String c(Model model) {
        model.addAttribute("username", "alibaba");
        model.addAttribute("age",19999);
        return "redirect:/c/{username}";
    }

    @RequestMapping("/c/{username}")
    public void c(@PathVariable("username") long username, HttpServletRequest request) {
        System.out.println(username);
    }

    @RequestMapping("/c2")
    public String c(HttpServletRequest request) {
        return "aaaa";
    }

    /*@RequestMapping("getData")
    public String getData() {
        return indexService.findUser();
    }*/

    @RequestMapping(value = "/user/logon", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            return "500";
        }
        return "login";
    }
}