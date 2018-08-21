package xiangzi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xiangzi.config.DataSourceConfig;
import xiangzi.config.RootConfig;
import xiangzi.config.WebConfig;


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
//    @Autowired
//    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer;
    @Autowired
    ProfileController profileController;
    @Value("${spring.datasource.type}")
    private String type;


    @RequestMapping("index")
    public String index() {
        String str = "reload2";
        return str + type;
    }

    /*@RequestMapping("getData")
    public String getData() {
        return indexService.findUser();
    }*/

}