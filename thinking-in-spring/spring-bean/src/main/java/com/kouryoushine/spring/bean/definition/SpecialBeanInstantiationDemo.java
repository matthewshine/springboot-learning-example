package com.kouryoushine.spring.bean.definition;

import com.kouryoushine.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @ClassName SpecialBeanInstantiationDemo
 * @Description 特殊实现方式
 * @Author kouryoushine
 * @Date 2021/7/7 0:00
 * @Version 1.0
 */
public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-creation-context.xml");

    }

    public static  void demoServiceLoader(){
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            UserFactory next = iterator.next();
            System.out.println(next.createUser());
        }

    }

}
