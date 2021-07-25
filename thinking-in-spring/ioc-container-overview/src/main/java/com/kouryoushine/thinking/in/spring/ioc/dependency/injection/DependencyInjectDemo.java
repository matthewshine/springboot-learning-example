package com.kouryoushine.thinking.in.spring.ioc.dependency.injection;

import com.kouryoushine.thinking.in.spring.ioc.dependency.annotation.Super;
import com.kouryoushine.thinking.in.spring.ioc.dependency.domain.User;
import com.kouryoushine.thinking.in.spring.ioc.dependency.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * @ClassName DependencyLookUpDemo
 * @Description
 *  1. 通过名称查找
 *  2. 通过类型查找
 * @Author kouryoushine
 * @Date 2021/6/30 23:06
 * @Version 1.0
 */
public class DependencyInjectDemo {
    public static void main(String[] args) {
        //配置xml文件，启动spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:\\META-INF\\DependencyInjection.xml");
        lookupInRealTime(beanFactory);


    }


    private static void lookupInRealTime (BeanFactory beanFactory){
        UserRepository user =(UserRepository) beanFactory.getBean("userRepository");

        //依赖来源一：自定义bean
        System.out.println("实时查找"+user.toString());

        //beanFacotory和springbean不是同一个对象，beanFacotry不是一个普通的bean

        //依赖注入
        //依赖来源二：内建依赖
        System.out.println(beanFactory==user.getBeanFactory());

        //依赖查找
//        System.out.println(beanFactory.getBean(BeanFactory.class));

        ObjectFactory<User> objectFactory = user.getObjectFactory();

        System.out.println(objectFactory==beanFactory);

        ObjectFactory objectFactoryc = user.getObjectFactoryc();
        System.out.println(objectFactoryc==beanFactory);

        //容器内建bean-容器初始化时候已经建好

        //依赖来源三：容器内部初始化的bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.print("获取Environment类型bean"+environment);

    }

    public static void whoIsIocContainer(String[] args) {


    }


}
