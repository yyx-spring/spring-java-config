package com.main.controller;

import com.main.service.CommonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


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

    @Test
    public void testHomePage() throws Exception {
        ProfileController controller = new ProfileController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/")).andExpect(view().name("home"));
    }

}