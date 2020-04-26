package com.kouryoushine.aop.chain;

public abstract class Handler {

    private Handler sucessor;

    public void  execute(){
        handleProcess();   //首先调用自己的方法
        if(sucessor!=null){  //如果上级存在则调用上级的sucessor
               sucessor.execute();
        }
    }
    protected  abstract void handleProcess();


    public Handler getSucessor() {
        return sucessor;
    }

    public void setSucessor(Handler sucessor) {
        this.sucessor = sucessor;
    }
}
