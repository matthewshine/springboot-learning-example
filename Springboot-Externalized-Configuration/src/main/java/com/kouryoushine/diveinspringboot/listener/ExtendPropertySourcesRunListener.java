package com.kouryoushine.diveinspringboot.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ExtendPropertySourcesRunListener
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/10/11 16:51
 * @Version 1.0
 */
public class ExtendPropertySourcesRunListener implements SpringApplicationRunListener, Ordered {
    private final SpringApplication application;
    private  final String [] args;
    public ExtendPropertySourcesRunListener(SpringApplication application, String[] args) {
        this.application=application;
        this.args=args;
    }

    public void starting() {

    }

    public void environmentPrepared(ConfigurableEnvironment environment) {

        Map<String,Object> map =new HashMap<String, Object>();
        map.put("user.id",100);
        MutablePropertySources propertySources = environment.getPropertySources();
        MapPropertySource mapPropertySource = new MapPropertySource("from-environmentPrepared",map);
        propertySources.addFirst(mapPropertySource);
    }

    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    public void started(ConfigurableApplicationContext context) {

    }

    public void running(ConfigurableApplicationContext context) {

    }

    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }

    public int getOrder() {
        //确保自定义启动顺序靠前
        return new EventPublishingRunListener(application,args).getOrder()+1;
    }
}
