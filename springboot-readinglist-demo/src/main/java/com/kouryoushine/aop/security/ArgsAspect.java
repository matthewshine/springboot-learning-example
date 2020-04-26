package com.kouryoushine.aop.security;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ArgsAspect {

    @Pointcut("args(Long,..) && within(com.kouryoushine.aop.service.*)")  //匹配第一个参数为long的,在service包下的
    public void  argsMatch(){
        
    }

    @Before("argsMatch()")
    public void  check(){
        System.out.println("在匹配参数的所有方法切入");

    }
}
