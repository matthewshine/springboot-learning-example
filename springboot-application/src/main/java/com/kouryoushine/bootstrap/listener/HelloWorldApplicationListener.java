package com.kouryoushine.bootstrap.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 监听ContextRefreshedEvent事件
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelloWorldApplicationListener  implements ApplicationListener <ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Helloworld:"+event.getApplicationContext().getId()+" timestamp:"+event.getTimestamp());
    }
}
