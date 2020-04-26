package com.kouryoushine.aop.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ObjectAspect {

    @Pointcut("this(com.kouryoushine.aop.service.ProductService)")
    public void  objMatch(){
        
    }

    @Before("objMatch()")
    public void  check(){
        System.out.println("在匹配对象的所有方法切入");

    }
}
