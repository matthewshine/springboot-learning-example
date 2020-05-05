package com.kouryoushine.bootstrap;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


/**
 * spring application context bootstrap
 */
@SpringBootApplication
public class SpringApplicationContextBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringApplicationContextBootstrap.class)
                    .run(args);

        // AnnotationConfigServletWebServerApplicationContext Servlet类型
        System.out.println("ConfigurableApplicationContext 类型："+context.getClass().getName());
        System.out.println("Environment 类型："+context.getEnvironment().getClass().getName());
        context.close();
    }
}
