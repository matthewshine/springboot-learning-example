package com.kouryoushine.autoconfigdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName HelloAutoConfiguration
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/5/23 21:19
 * @Version 1.0
 */
@EnableConfigurationProperties(HelloServiceProperties.class)
@Configuration
public class HelloAutoConfiguration {

    @Autowired
    HelloServiceProperties properties;

    @Bean
    HelloService helloService(){
        System.out.println("自动装配属性"+properties.getName());
        return new HelloService();
    }

}
