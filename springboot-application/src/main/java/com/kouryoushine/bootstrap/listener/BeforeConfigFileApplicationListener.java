package com.kouryoushine.bootstrap.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.RandomValuePropertySource;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ResourceLoader;

public class BeforeConfigFileApplicationListener implements  SmartApplicationListener, Ordered {

    @Override
    public int getOrder() {
        //DEFAULT_ORDER-1的话就获取不到参数，+1就可以
        return ConfigFileApplicationListener.DEFAULT_ORDER-1;
    }


    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return ApplicationEnvironmentPreparedEvent.class.isAssignableFrom(eventType) || ApplicationPreparedEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            ApplicationEnvironmentPreparedEvent event1= (ApplicationEnvironmentPreparedEvent) event;
            ConfigurableEnvironment environment = event1.getEnvironment();

            System.out.println("配置文件参数"+environment.getProperty("name"));
        }

        if (event instanceof ApplicationPreparedEvent) {
        }

    }




}
