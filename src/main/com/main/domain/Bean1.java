package com.main.domain;


import jdk.nashorn.internal.objects.annotations.Setter;

public class Bean1 {

    private String name;
    private int age;

    public void showName(){
        System.out.println(this.getClass().getName());
    }


}
