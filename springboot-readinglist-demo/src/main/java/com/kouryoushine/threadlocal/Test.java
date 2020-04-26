package com.kouryoushine.threadlocal;

public class Test {

    static MyThreadLocal<Long> myThreadLocal = new MyThreadLocal<Long>(){
        @Override
        protected Long initialVlaue() {
            return Thread.currentThread().getId();
        }
    };
    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(()->{
                System.out.println(myThreadLocal.get());
            }).start();
        }
    }
}
