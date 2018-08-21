package main.java.controller;

import main.java.config.DataSourceConfig;
import main.java.config.RootConfig;
import main.java.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Profile("test")
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



    @RequestMapping("test")
    public String index() {
        return "THIS IS A TEST.WELCOME";
    }

    /*@RequestMapping("getData")
    public String getData() {
        return indexService.findUser();
    }*/

}