package com.kouryoushine.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;
import java.util.Set;


public class SpringApplicationBootstrap {

    public static void main(String[] args) {

        Set<String> sources = new HashSet<>();
        sources.add(ApplicationConfiguration.class.getName());
//        SpringApplication.run(ApplicationConfiguration.class,args);
        //配置Class源名称
        SpringApplication springApplication = new SpringApplication();
        springApplication.setSources(sources);
//        springApplication.setWebApplicationType(WebApplicationType.NONE);//使用非web类型
        ConfigurableApplicationContext context = springApplication.run(args);


        System.out.println(context.getBean(ApplicationConfiguration.class) );

    }

    @SpringBootApplication
    public static class  ApplicationConfiguration{

    }
}
