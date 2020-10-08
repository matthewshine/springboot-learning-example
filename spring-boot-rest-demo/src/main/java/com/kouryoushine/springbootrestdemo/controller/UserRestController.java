package com.kouryoushine.springbootrestdemo.controller;

import com.kouryoushine.springbootrestdemo.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserRestController
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/10/8 13:10
 * @Version 1.0
 */
@RestController
public class UserRestController {

    @PostMapping(value = "/echo/user",produces = "application/json  ")
    public User user(@RequestBody User user){
        return  user;
    }
}
