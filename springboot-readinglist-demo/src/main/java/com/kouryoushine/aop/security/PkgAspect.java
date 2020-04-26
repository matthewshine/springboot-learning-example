package com.kouryoushine.aop.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PkgAspect {

    /**
     * 这里是within 而不是@within 一定要区别
     */

    //拦截包下所有类所有方法
    @Pointcut("within(com.kouryoushine.aop.service.*)")
    public void matchType(){}


    @Before("matchType()")
    public void  check(){
        System.out.println("在指定类路径下所有方法切入");
    }
}
