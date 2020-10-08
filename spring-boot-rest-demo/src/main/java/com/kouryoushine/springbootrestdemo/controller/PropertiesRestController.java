package com.kouryoushine.springbootrestdemo.controller;

import com.kouryoushine.springbootrestdemo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * @ClassName UserRestController
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/10/8 13:10
 * @Version 1.0
 */
@Controller
//@RestController
public class PropertiesRestController {

    @PostMapping(value = "/echo/properties",consumes = "text/properties;charset=UTF-8")
    public Properties user(@RequestBody Properties properties){
        //properties经过转后自动变成输入流和输出流
        return  properties;
    }


    @PostMapping(value = "/resolve/properties",consumes = "text/properties;charset=UTF-8")
    public Properties resolveProperties ( Properties properties){
        //properties经过转后自动变成输入流和输出流
        return  properties;
    }
}
