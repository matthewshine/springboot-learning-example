package com.kouryoushine.spring.bean.definition;

import com.kouryoushine.thinking.in.spring.ioc.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName BeanInstantinationDemo
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/7/6 23:40
 * @Version 1.0
 */
public class BeanInstantinationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-creation-context.xml");
        User user = beanFactory.getBean("user-by-static-method",User.class);

        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        User user1 = beanFactory.getBean("user-by-factorybean", User.class);

        System.out.println(user);
        System.out.println(userByInstanceMethod);
        System.out.println(user1);


    }
}
