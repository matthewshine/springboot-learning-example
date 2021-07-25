package com.kouryoushine.thinking.in.spring.ioc.dependency.container;

import com.kouryoushine.thinking.in.spring.ioc.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @ClassName BeanFactoryAsIocContainerDemo
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/7/4 23:30
 * @Version 1.0
 */
@Configuration
public class AnnotationApplicationContextAsIocContainerDemo {
    public static void main(String[] args) {
        //创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类作为配置类，查找@Bean注解的Bean
        applicationContext.register(AnnotationApplicationContextAsIocContainerDemo.class);
        //刷新应用上下文
        applicationContext.refresh();
        //XML配置文件路径
        lookupCollectionByType(applicationContext);

    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory beanFactory1 = (ListableBeanFactory) beanFactory;
            Map<String, User> users = beanFactory1.getBeansOfType(User.class);
            System.out.println("所有集合" + users);

        }

    }

    @Bean
    public User user(){
        return  new User();
    }
}
