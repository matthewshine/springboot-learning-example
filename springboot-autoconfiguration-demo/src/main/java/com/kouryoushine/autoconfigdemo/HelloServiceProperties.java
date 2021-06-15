package com.kouryoushine.autoconfigdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName HelloServiceProperties
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/5/23 21:18
 * @Version 1.0
 */

@ConfigurationProperties(prefix = "hello")
public class HelloServiceProperties {
    private String name ;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
