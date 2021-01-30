package com.kouryoushine.diveinspringboot;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @ClassName ExtendPropertySourcesBootstrap
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/10/11 17:02
 * @Version 1.0
 */
@EnableAutoConfiguration
public class ExtendPropertySourcesBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new
                        SpringApplicationBuilder(ExtendPropertySourcesBootstrap.class)
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run(args);

        ConfigurableEnvironment environment = context.getEnvironment();
        Long property = environment.getProperty("user.id", Long.class);
        System.out.println("从environment获取 id"+property);
        context.close();
    }


}
