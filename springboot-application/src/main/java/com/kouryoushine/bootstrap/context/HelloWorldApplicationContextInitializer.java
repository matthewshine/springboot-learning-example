package com.kouryoushine.bootstrap.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelloWorldApplicationContextInitializer <C extends ConfigurableApplicationContext> implements ApplicationContextInitializer <C>{
    //C要继承ConfigurableApplicationContext

    @Override
    public void initialize(C applicationContext) {
        System.out.println("applicationContext.getId()"+applicationContext.getId());

    }
}
