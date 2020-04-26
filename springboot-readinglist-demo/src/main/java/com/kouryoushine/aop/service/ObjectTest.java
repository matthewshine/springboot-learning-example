package com.kouryoushine.aop.service;

import com.kouryoushine.annotation.TargetBean;
import org.springframework.stereotype.Service;

@Service
@TargetBean
public class ObjectTest {

    public void  test1(){
        System.out.println("+++ObjectTest1++++");
    }


    public void  test2(Long st){
        System.out.println(st);
        System.out.println("+++ObjectTest2++++");
    }
}
