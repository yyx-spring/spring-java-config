package com.main.intercepter;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommonAspect {

    @Pointcut("execution(* com.main.service.CommonService.*(..))")
    public void pointExecution(){

    }

    @Before("execution(* com.main.service.CommonService.*(..))")
    public void before(){
        System.out.println("Before>>>>>>>>>>>>>>>>");
    }

    @After("pointExecution()")
    public void after(){
        System.out.println("After>>>>>>>>>>>>>>>>");
    }

    @AfterReturning("pointExecution()")
    public void afterRerurning(){
        System.out.println("AfterReturning>>>>>>>>>>>>>>>>");
    }

    @AfterThrowing("pointExecution()")
    public void afterThrowing(){
        System.out.println("AfterThrowing>>>>>>>>>>>>>>>>");
    }

    /*@Around("pointExecution()")
    public void around(){
        System.out.println("Around>>>>>>>>>>>>>>>>");
    }*/

}
