package com.kouryoushine.spring.bean.definition;

import com.kouryoushine.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName BeanGarbageCollection
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/7/8 0:25
 * @Version 1.0
 */

public class BeanGarbageCollection {
    public static void main(String[] args) {
        //创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        //启动spring上下文
        applicationContext.refresh();

        //关闭spring上下文
        applicationContext.close();

        //强制gc
        System.gc();

    }
}
