package com.main.rpc;

/**
 * @Author: yunxiang.yang
 * @Date: 2018/11/9 16:49
 */

import org.springframework.stereotype.Service;


@Service
public class RpcService {


    public void getUserInfo(final Long id) {
        System.out.println(this.getClass() + ":" + id);
    }

}

