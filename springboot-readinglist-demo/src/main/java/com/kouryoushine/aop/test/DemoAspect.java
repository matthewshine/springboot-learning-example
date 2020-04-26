package com.kouryoushine.aop.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Aspect
@Component
public class DemoAspect {

    //切入点：aopdemo报下所有对象的save方法
    @Pointcut("execution(public * com.kouryoushine.aop.test.*.save*(..))")
    public void save(){

    }
    /**
     * 需要在update操作前后分别获取更新前后的值
     * @param
     * @return
     */

    @AfterReturning("save()")
    public void afterReturn(JoinPoint joinPoint) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        //1.获取切入点所在目标对象
        Object targetObj =joinPoint.getTarget();
        System.out.println(targetObj.getClass().getName());
        // 2.获取切入点方法的名字
        String methodName = joinPoint.getSignature().getName();
        System.out.println("切入方法名字："+methodName);
        // 3. 获取方法上的注解
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
           ApiLog apiLog=  method.getAnnotation(ApiLog.class);
            System.out.println("切入方法注解的title:"+apiLog.title());
        }

        //4. 获取方法的参数
        Object[] args = joinPoint.getArgs();
        for(Object o :args){
            System.out.println("切入方法的参数："+o);
        }


    }



}
