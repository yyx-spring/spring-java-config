package com.main.controller;

import com.main.config.DataSourceConfig;
import com.main.config.RootConfig;
import com.main.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {
    /*@Autowired
    private IndexService indexService;
*/

    @Autowired
    DataSourceConfig dataSourceConfig;
    @Autowired
    RootConfig rootConfig;
    @Autowired
    WebConfig webConfig;



    @RequestMapping("index")
    public String index() {
        return "THIS IS A TEST.WELCOME";
    }

    /*@RequestMapping("getData")
    public String getData() {
        return indexService.findUser();
    }*/

}