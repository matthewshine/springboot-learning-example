package com.kouryoushine.aop.proxymodel.dynamic;

import com.kouryoushine.aop.proxymodel.RealSubject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 代理类必须实现InvocationHandler接口，包含了invoke方法
 */

public class JdkProxySubject implements InvocationHandler {
    private RealSubject realSubject;

    public JdkProxySubject(RealSubject realSubject) {
        this.realSubject=realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("执行动态代理逻辑--before");
        Object result=null;
        try {
             result = method.invoke(realSubject, args); //通过反射调用目标对象的方法
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            System.out.println("after");
        }

        return result;
    }
}
