package com.main.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author admin
 * @date 2018/10/13 21:31
 */
@Service
public class CachingService {

    //第一次先查缓存，不存在则执行；之后查缓存，不再执行该方法
    @Cacheable("test")
    public String cacheable(long id){
        System.out.println(id);
        return "test";
    }
}
