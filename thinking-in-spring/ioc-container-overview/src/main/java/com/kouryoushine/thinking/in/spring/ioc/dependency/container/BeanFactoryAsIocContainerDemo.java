package com.kouryoushine.thinking.in.spring.ioc.dependency.container;

import com.kouryoushine.thinking.in.spring.ioc.dependency.annotation.Super;
import com.kouryoushine.thinking.in.spring.ioc.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @ClassName BeanFactoryAsIocContainerDemo
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/7/4 23:30
 * @Version 1.0
 */
public class BeanFactoryAsIocContainerDemo {
    public static void main(String[] args) {
        //创建BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //XML配置文件路径
        String location = "classpath:/META-INF/DependencyLookUp.xml";
        //加载配置
        int beanNums = reader.loadBeanDefinitions(location);
        System.out.println(beanNums);
        lookupCollectionByType(beanFactory);

    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory beanFactory1 = (ListableBeanFactory) beanFactory;
            Map<String, User> users = beanFactory1.getBeansOfType(User.class);
            System.out.println("所有集合" + users);

        }

    }
}
