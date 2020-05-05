package com.kouryoushine.web.controller;

/**
 * 该部分代码会在HelloWorldController前执行
 */

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(assignableTypes = HelloWorldController.class)
public class HelloWorldControllerAdvice {


    //注入固定的参数,这种方式不需要在每个方法上标注
    @ModelAttribute("jsessionid")
    public String message(@CookieValue("JSESSIONID") String jsessionid){
        System.out.println(jsessionid);
        return jsessionid;
    }
    //注入固定的参数
    @ModelAttribute("message")
    public String message(){
        return "HelloWorld";
    }
}
