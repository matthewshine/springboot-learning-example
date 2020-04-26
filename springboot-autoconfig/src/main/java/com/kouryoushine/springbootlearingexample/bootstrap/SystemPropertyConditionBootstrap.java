package com.kouryoushine.springbootlearingexample.bootstrap;

import com.kouryoushine.springbootlearingexample.condition.ConditionalOnSystemProperty;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 系统属性判断
 */

public class SystemPropertyConditionBootstrap {

    //条件满足是才会装载helloWorldbean
    @Bean
    @ConditionalOnSystemProperty(name = "user.name",value = "Administrator")
    public String helloWorld(){
        return "Hello WOrld Admin";
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(SystemPropertyConditionBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String  helloworld = context.getBean("helloWorld",String.class);
        System.out.println(helloworld);
        //close context
        context.close();

    }
}
