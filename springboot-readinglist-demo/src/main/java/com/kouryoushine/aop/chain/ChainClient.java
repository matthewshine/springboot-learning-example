package com.kouryoushine.aop.chain;

import java.util.Arrays;
import java.util.List;

public class ChainClient {

    public static void main(String[] args) {

        ChainHandler chainHandlerA = new ChainHandler() {
            @Override
            protected void handleprocess() {
                System.out.println("Handler By A");
            }
        };

        ChainHandler chainHandlerB = new ChainHandler() {
            @Override
            protected void handleprocess() {
                System.out.println("Handler By B");
            }
        };

        ChainHandler chainHandlerC = new ChainHandler() {
            @Override
            protected void handleprocess() {
                System.out.println("Handler By C");
            }
        };

        List<ChainHandler> list = Arrays.asList( //生命责任链关系
                chainHandlerA,chainHandlerB,chainHandlerC
        );
        Chain chain = new Chain(list);
        chain.controlprocess();


    }
}
