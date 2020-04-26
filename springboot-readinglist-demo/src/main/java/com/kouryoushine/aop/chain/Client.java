package com.kouryoushine.aop.chain;

public class Client {

    static class HandleA extends Handler {
        @Override
        protected void handleProcess() {
            System.out.println("hanldle by A");
        }
    }

    static class HandleB extends Handler {
        @Override
        protected void handleProcess() {
            System.out.println("hanldle by B");
        }
    }

    static class HandleC extends Handler {
        @Override
        protected void handleProcess() {
            System.out.println("hanldle by C");
        }
    }

    public static void main(String[] args) {

        Handler handlerA = new HandleA();
        Handler handlerB = new HandleB();
        Handler handlerC = new HandleC();

        handlerA.setSucessor(handlerB);
        handlerB.setSucessor(handlerC);

        handlerA.execute();



    }
}
