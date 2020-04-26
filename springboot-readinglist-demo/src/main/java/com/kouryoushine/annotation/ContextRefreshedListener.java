package com.kouryoushine.annotation;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    public static Map<String, Object> beanMap = new HashMap<>();
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //获取标注有TargetBean注解的bean
      beanMap = event.getApplicationContext().getBeansWithAnnotation(TargetBean.class);

        System.out.println(beanMap);
    }
}
