package com.example.back.exback.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.example.back.exback.api.controller..*(..))")
    public void allController() {}

    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {}

    @Pointcut("allController() & allService()")
    public void controllerAndService() {}

    @Pointcut("execution(* com.example.back.exback.api..*(..))")
    public void apiAll() {}
}
