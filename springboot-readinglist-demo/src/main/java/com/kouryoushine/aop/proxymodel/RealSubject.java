package com.kouryoushine.aop.proxymodel;

public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("执行目标对象的方法");
    }
}
