package com.main.controller;

import com.main.domain.Bean1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yunxiang.yang
 * @Date: 2018/10/22 16:20
 */

@Controller
@RequestMapping("/rest")
public class RestController {


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
}
