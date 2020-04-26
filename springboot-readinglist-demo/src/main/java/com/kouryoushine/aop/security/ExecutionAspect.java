package com.kouryoushine.aop.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionAspect {


    //参数：修饰符  返回值 类全路径 方法和参数
    @Pointcut("execution(public String com.kouryoushine.aop.service.*Service.*(..))")  //public修饰的，String返回值，以Service结尾的类的任意名字，任意参数的方法
    public void  execuMatch(){

    }

    @Before("execuMatch()")
    public void  check(){
        System.out.println("在匹配方法返回参数的所有方法切入");

    }
}
