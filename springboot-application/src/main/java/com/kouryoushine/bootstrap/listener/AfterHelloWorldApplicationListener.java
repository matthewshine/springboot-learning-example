package com.kouryoushine.bootstrap.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 监听ContextRefreshedEvent事件
 *
 * 相同事件进行排序
 */
@Order(Ordered.LOWEST_PRECEDENCE)
public class AfterHelloWorldApplicationListener implements ApplicationListener <ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("After Helloworld: "+event.getApplicationContext().getId()+" timestamp: "+event.getTimestamp());
    }
}
