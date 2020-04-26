package com.kouryoushine.springbootlearingexample.bootstrap;


import com.kouryoushine.springbootlearingexample.service.CalculateService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.kouryoushine.springbootlearingexample.service")
public class CalculateServiceBootStrap {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(CalculateServiceBootStrap.class)
                .web(WebApplicationType.NONE)
                .profiles("java7")
                .run(args);


        CalculateService calculateService = context.getBean(CalculateService.class);

        System.out.println("calculateService sum(1...10):"+calculateService.sum(1,2,3,4,5,6,7,8,9,10));

        //close context
        context.close();

    }
}
