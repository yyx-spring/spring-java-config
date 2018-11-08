package com.main.listener;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: yunxiang.yang
 * @Date: 2018/11/8 14:58
 */

public class JmsHandler {
    public void handleMessage(String message) {
        System.out.println("String:" + message);
    }

    public void handleMessage(Map message) {
        System.out.println("Map:" + message);
    }

    public void handleMessage(byte[] message) {
        System.out.println("byte[]:" + message);
    }

    public void handleMessage(Serializable message) {
        System.out.println("Serializable:" + message);
    }

}