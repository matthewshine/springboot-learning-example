package com.kouryoushine.springbootview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/10/6 22:27
 * @Version 1.0
 */
@Controller
public class HelloController {

    @GetMapping("/hello-world")
    public String helloWorld(Model model){
        model.addAttribute("message","moleee");
        return "hello-world"; //view 逻辑名称
    }

    //定义变量放入上下文环境
    @ModelAttribute("message")
    public String message(){
        return  "hello message world";
    }
}
