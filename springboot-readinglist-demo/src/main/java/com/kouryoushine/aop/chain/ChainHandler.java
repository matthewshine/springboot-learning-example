package com.kouryoushine.aop.chain;


/**
 * 加强版责任链模式
 */
public abstract class ChainHandler {

    //Handler中获取到责任链关系
    public  void  execute(Chain chain){
            handleprocess();//首先执行自己的handlerprocess方法；
            chain.controlprocess(); //递归调用chain的方法
    }
    protected abstract void handleprocess();
}
