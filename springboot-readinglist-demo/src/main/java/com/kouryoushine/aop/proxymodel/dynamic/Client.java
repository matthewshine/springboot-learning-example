package com.kouryoushine.aop.proxymodel.dynamic;

import com.kouryoushine.aop.proxymodel.RealSubject;
import com.kouryoushine.aop.proxymodel.Subject;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        Subject subject = (Subject) Proxy.newProxyInstance(Client.class.getClassLoader(),
                new Class[]{Subject.class},new JdkProxySubject(new RealSubject()));
        subject.request();

    }
}
