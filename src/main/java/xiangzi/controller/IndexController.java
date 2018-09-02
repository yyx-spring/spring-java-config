package com.main.controller;

import com.main.config.DataSourceConfig;
import com.main.config.RootConfig;
import com.main.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {
    /*@Autowired
    private IndexService indexService;
*/

    @Autowired
    CommonService commonService;
    @Autowired
    ProfileController profileController;
    @Value("${spring.datasource.type}")
    private String type;


    @RequestMapping("index")
    public String index() {
        commonService.testService();
        return "THIS IS A TEST.WELCOME" + type;
    }

    /*@RequestMapping("getData")
    public String getData() {
        return indexService.findUser();
    }*/

}