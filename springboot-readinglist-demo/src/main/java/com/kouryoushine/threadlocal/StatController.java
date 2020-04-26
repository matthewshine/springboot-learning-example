package com.kouryoushine.threadlocal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threadlocal")
public class StatController {

    //spring是单例模式，所有线程共享一个对象，这里定义的变量会被所有线程共享
    static  Integer c=0;

    ThreadLocal<Integer> threadc= new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    @RequestMapping("/stat")
    public Integer stat(){
        return c;
    }


    //单线程下是安全的，多线程结果是错误的
    @RequestMapping("/add")
    public Integer add(){
        c++;
        return 1;
    }


    //加锁后线程安全，但是响应速度明显变慢
    @RequestMapping("/addsyc")
    public Integer addsyc(){
        addsycn();
        return 1;
    }

    @RequestMapping("/addthreadlocal")
    public Integer addthreadlocal(){

        threadc.set(threadc.get()+1); //这种方式虽然可以让threadc相加但世界上threadc是每个变量车都有一份的，我们需要把变量累加起来
        return 1;
    }


    synchronized Integer  addsycn(){
        c++;
        return 1;
    }


    @RequestMapping("/sleep")
    public Integer sleep(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 10;
    }
}
