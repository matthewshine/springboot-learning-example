package com.kouryoushine.aop.service;

import com.kouryoushine.annotation.TargetBean;
import com.kouryoushine.aop.security.AdminOnly;
import org.springframework.stereotype.Service;

@Service
@TargetBean
public class ProductService {


    //标注了AdminOnly注解的方法就会被aop切入
    @AdminOnly
    public void  insert(){
        System.out.println("insert");
    }

    public void  update(){
        System.out.println("update");
    }


    public String  test(){
        System.out.println("test");
        return "";
    }
}
