package com.kouryoushine.springbootapplication.annotation;

import com.kouryoushine.springbootapplication.configuration.HelloWorldConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;


@Configuration
public class HelloWorldImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        //在选择其中可以选择不同的Helloworld实现方式
        return  new String[]{HelloWorldConfiguration.class.getName()};
    }
}
