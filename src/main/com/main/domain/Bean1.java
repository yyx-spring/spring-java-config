package com.main.domain;


import java.io.Serializable;

public class Bean1 implements Serializable {

    private String name;
    private int age;

    Bean1() {
    }

    public Bean1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void showName() {
        System.out.println(this.getClass().getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
