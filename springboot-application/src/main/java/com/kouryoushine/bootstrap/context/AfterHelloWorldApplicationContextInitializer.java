package com.kouryoushine.bootstrap.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


public class AfterHelloWorldApplicationContextInitializer implements  ApplicationContextInitializer,Ordered {
    //C要继承ConfigurableApplicationContext

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println(" after applicationContext.getId()"+applicationContext.getId());

    }

    //和@Order注解效果相同
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
