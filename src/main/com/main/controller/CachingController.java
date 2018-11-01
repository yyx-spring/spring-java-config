package com.main.controller;

import com.main.service.CachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author admin
 * @date 2018/10/13 21:29
 */
@Controller
@RequestMapping("/caching")
public class CachingController {

    @Autowired
    CachingService cachingService;

    @RequestMapping("/cacheable")
    public void getCaching(){
        String s1 = cachingService.cacheable(1);
        String s2 = cachingService.cacheable(15);
    }


}
