package com.kouryoushine.springbootlearingexample.bootstrap;

import com.kouryoushine.springbootlearingexample.annotation.EnableHelloWorld;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@EnableHelloWorld
public class EnableHelloWorldBootstrap {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableHelloWorldBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);


        String helloWorld = context.getBean("helloWorld", String.class);

        System.out.println("helloworld Bean:"+helloWorld);

        //close context
        context.close();

    }

}
