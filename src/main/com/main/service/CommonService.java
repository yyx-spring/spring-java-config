package com.main.service;

import org.springframework.stereotype.Service;

@Service
public class CommonService {

    public void testService(){
        System.out.println(this.getClass().getName());
    }

}
