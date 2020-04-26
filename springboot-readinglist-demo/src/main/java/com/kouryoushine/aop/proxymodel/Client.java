package com.kouryoushine.aop.proxymodel;

public class Client {

    public static void main(String[] args) {

        ProxySubject proxySubject = new ProxySubject(new RealSubject());
        proxySubject.before();
        proxySubject.request();
        proxySubject.after();

    }
}
