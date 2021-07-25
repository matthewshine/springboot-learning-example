package com.kouryoushine.spring.bean.definition;

import com.kouryoushine.spring.bean.factory.DefaultUserFactory;
import com.kouryoushine.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName SingletonBeanRegistrationDemo
 * @Description 注册外部实例到容器上下文
 * @Author kouryoushine
 * @Date 2021/7/8 0:31
 * @Version 1.0
 */
public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) {
        //创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory applicationContextBeanFactory = applicationContext.getBeanFactory();
        applicationContextBeanFactory.registerSingleton("userFactory",userFactory);

        //启动spring上下文
        applicationContext.refresh();


//        通过依赖查找获取bean
        Object userFactory1 = applicationContextBeanFactory.getBean("userFactory");
        System.out.println(userFactory==userFactory1);



        //关闭spring上下文
        applicationContext.close();

    }
}
