package com.kouryoushine.aop.security;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//声明为一个切面
@Aspect
@Component
public class SercurityAspect {

    @Autowired
    AuthService authService;  //注入校验的服务

    //切入点表达式
    // 对于标注了AdminOnly注解的方法切入
    @Pointcut("@annotation(AdminOnly)")
    public void  adminOnly(){
    }

    //声明advice告诉何时插入，在Adminonly注解方法的做前置通知。  参数是pointcut的方法名
    @Before("adminOnly()")
    public void check(){
        authService.checkAccess();
    }
}
