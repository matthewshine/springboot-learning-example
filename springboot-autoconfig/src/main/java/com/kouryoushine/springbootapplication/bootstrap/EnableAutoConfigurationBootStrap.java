package com.kouryoushine.springbootapplication.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@EnableAutoConfiguration
public class EnableAutoConfigurationBootStrap {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableAutoConfigurationBootStrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String helloWorld = context.getBean("helloWorld", String.class);
        System.out.println("helloworld Bean:"+helloWorld);
        //close context
        context.close();

    }
}
