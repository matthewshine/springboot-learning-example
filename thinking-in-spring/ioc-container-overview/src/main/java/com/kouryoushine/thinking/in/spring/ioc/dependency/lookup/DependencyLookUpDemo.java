package com.kouryoushine.thinking.in.spring.ioc.dependency.lookup;

import com.kouryoushine.thinking.in.spring.ioc.dependency.annotation.Super;
import com.kouryoushine.thinking.in.spring.ioc.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
public class DependencyLookUpDemo {
    public static void main(String[] args) {
        //配置xml文件，启动spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:\\META-INF\\DependencyLookUp.xml");

        lookUpInLazy(beanFactory);
        //按照类型查找
        lookupByType(beanFactory);
        //通过注解查找
        lookupByAnnnotation(beanFactory);
    }

    private static void lookupByAnnnotation(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory beanFactory1 = (ListableBeanFactory) beanFactory;
          Map<String, Object> users = beanFactory1.getBeansWithAnnotation(Super.class);
            System.out.println("所有@Super注解标注集合"+users);

        }
    }

    private  static  void lookupByType(BeanFactory beanFactory){
        User bean = beanFactory.getBean(User.class);
        System.out.println("按照类型查找"+bean);

    }

    private static void lookupInRealTime (BeanFactory beanFactory){
        User user =(User) beanFactory.getBean("user");
        System.out.println("实时查找"+user.toString());
    }

    private static void lookUpInLazy (BeanFactory beanFactory){
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean(ObjectFactory.class);
        User user = objectFactory.getObject();
        System.out.println("延时查找"+user.toString());
    }
}
