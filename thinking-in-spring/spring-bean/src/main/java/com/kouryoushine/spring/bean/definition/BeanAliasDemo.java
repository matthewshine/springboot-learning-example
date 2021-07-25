package com.kouryoushine.spring.bean.definition;

import com.kouryoushine.thinking.in.spring.ioc.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName BeanAliasDemo
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/7/6 0:51
 * @Version 1.0
 */
public class BeanAliasDemo {
    // 通过别名获取bean
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");
        User bean =(User) beanFactory.getBean("user-alians1");
        User bean2 =(User) beanFactory.getBean("user");
        System.out.println(bean);
        System.out.println(bean==bean2);
    }
}
