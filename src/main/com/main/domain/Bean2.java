package com.main.domain;

public class Bean2 {

    public void showName() {
        System.out.println(this.getClass().getName());
    }

    String name = "XXXX";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
