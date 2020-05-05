package com.kouryoushine.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class HelloWorldController {

    @RequestMapping
    public String index(@RequestHeader("Accept-Language") String acceptLanguage
   , Model model){
        //,@RequestParam int value

        //通过model设置参数
        model.addAttribute("acceptLanguage",acceptLanguage);
        System.out.println(acceptLanguage);
        return  "index";
    }
//
//    //注入固定的参数,这种方式不需要在每个方法上标注
//    @ModelAttribute("jsessionid")
//    public String message(@CookieValue("JSESSIONID") String jsessionid){
//        System.out.println(jsessionid);
//        return jsessionid;
//    }
//    //注入固定的参数
//    @ModelAttribute("message")
//    public String message(){
//        return "HelloWorld";
//    }

    //拦截所有异常
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handle(Exception ex){
        System.out.println(ex.getMessage());
        //拦截异常并返回异常信息
        return ResponseEntity.ok(ex.getMessage());
    }
}
