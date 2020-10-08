package com.kouryoushine.springbootrestdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/10/8 11:09
 * @Version 1.0
 */

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(String hello){
        return "helloworld"+hello;
    }
}
