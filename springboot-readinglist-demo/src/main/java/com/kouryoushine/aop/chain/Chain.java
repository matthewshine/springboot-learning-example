package com.kouryoushine.aop.chain;

import java.util.List;

public class Chain {

    //chain里封装了责任链关系
    private  List<ChainHandler> handlers;
    private  int index=0;
    // 通过构造器传入责任链关系
    public Chain(List<ChainHandler> handlers) {
        this.handlers = handlers;
    }


    //在chain中控制责任链执行过程

    public void  controlprocess(){
        if(index>=handlers.size()){ //防止下表越界
            return;
        }
        handlers.get(index++).execute(this); //依次执行责任链关系

    }


}
