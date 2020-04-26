package com.kouryoushine.springbootlearingexample.annotation;

import com.kouryoushine.springbootlearingexample.configuration.HelloWorldConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@Import(HelloWorldImportSelector.class)
@Import(HelloWorldConfiguration.class)
public @interface EnableHelloWorld {
}
