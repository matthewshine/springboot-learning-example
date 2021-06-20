package com.kouryoushine.mybatis.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName MybatisDemoApplication
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/6/15 22:05
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.kouryoushine.mybatis.demo.mapper")
public class MybatisDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class,args);
    }
}
