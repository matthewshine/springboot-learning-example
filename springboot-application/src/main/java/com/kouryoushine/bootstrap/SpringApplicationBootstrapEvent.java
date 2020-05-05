package com.kouryoushine.bootstrap;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplicationBootstrapEvent {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册监听器
        context.addApplicationListener(applicationEvent -> {
            System.out.println("监听到事件："+applicationEvent.toString());
        });

        //启动和关闭
        context.refresh();
        context.publishEvent("HelloEvent");
        context.publishEvent(new ApplicationEvent("Hello2") {
            @Override
            public Object getSource() {
                return super.getSource();
            }
        });
        context.close();
    }
}
