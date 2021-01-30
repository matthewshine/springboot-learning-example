package com.kouryoushine.diveinspringboot.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ExtendPropertySourcesEventLisener
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/10/11 17:14
 * @Version 1.0
 */
public class ExtendPropertySourcesEventLisener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {


    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        ConfigurableEnvironment environment =
                applicationEnvironmentPreparedEvent.getEnvironment();

        Map<String,Object> map =new HashMap<String, Object>();
        map.put("user.id",200);
        MutablePropertySources propertySources = environment.getPropertySources();
        MapPropertySource mapPropertySource = new MapPropertySource("from-environmentPrepared",map);
        propertySources.addFirst(mapPropertySource);

    }
}
