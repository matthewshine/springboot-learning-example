package com.kouryoushine.aop.test;

import org.springframework.stereotype.Service;

@Service
public class TestServcie {

    @ApiLog(title = "注解的标题",isSaveRequestData = false)
    public void save(String parm1,int parm2){
        System.out.println("执行目标对象的方法"+parm1+parm2);
    }


    public void  update(){
        System.out.println("没有注解的方法，不会被拦截");
    }
}
