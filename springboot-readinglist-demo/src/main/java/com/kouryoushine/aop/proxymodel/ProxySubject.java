package com.kouryoushine.aop.proxymodel;

public class ProxySubject implements Subject {

    private  RealSubject realSubject;
    public ProxySubject(RealSubject realSubject) {
        this.realSubject=realSubject;
    }
    public void  after(){
        System.out.println("after");
    }

    //对于真正的逻辑执行真实对象的方法
    @Override
    public void request() {
        realSubject.request();
    }
    public void  before(){
        System.out.println("before");
    }

}
