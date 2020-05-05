package com.kouryoushine.springbootapplication.configuration;

import com.kouryoushine.springbootapplication.annotation.EnableHelloWorld;
import com.kouryoushine.springbootapplication.condition.ConditionalOnSystemProperty;
import org.springframework.context.annotation.Configuration;

/**
 * HelloWorld自动装配
 */
@Configuration  //注解模式装配
@EnableHelloWorld  //enable模块装配
@ConditionalOnSystemProperty(name = "user.name",value = "Administrator") //条件装配
public class HelloWorldAutoConfiguration {

    //@EnableHelloWorld 避免了模块化的开发

}
