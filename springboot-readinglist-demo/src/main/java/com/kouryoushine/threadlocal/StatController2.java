package com.kouryoushine.threadlocal;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/test")
//@Scope("prototype")
public class StatController2 {

    @PostConstruct
    public void init() {
        //此处每次有新的请求进来的时候，都会实例化一个新的controller对象
        System.out.println("ProductListController-->init:,this:" + this);
    }


    //spring是单例模式，所有线程共享一个对象，这里定义的变量会被所有线程共享
    Integer c = 0;

    @RequestMapping("/stat")
    public Integer stat() {
        return c;
    }


    //单线程下是安全的，多线程结果是错误的
    @RequestMapping("/add")
    public Integer add() {
        ++c;
        return c;
    }

    private int i = 0; //非静态
    private static int a = 0;//静态

    @RequestMapping("/test1")
    public String toShow(ModelMap model) {
        System.out.println("-->request:,this:" + this);
        System.out.println((++i) + "-----" + (++a));
        return "hello";
    }


    @RequestMapping("/sleep")
    public Integer sleep() {
        try {
            Thread.sleep(10000);
            System.out.println("response"+System.currentTimeMillis()/1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 10;
    }
}
