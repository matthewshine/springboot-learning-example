package com.kouryoushine.springbootdemo.controller;

import com.kouryoushine.autoconfigdemo.HelloService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/10/12 0:18
 * @Version 1.0
 */
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/helloauto")
    public void helloauto(){
      helloService.printHello();
    }

    @GetMapping("/hello")
    public User hello(){
        User user = new User();
        user.setCards(null);
        user.setName(null);


        return user;
    }

}
@Data
class User{
    private Long id;
    private String name;
    private List<String> cards;
    private Date date;
}
