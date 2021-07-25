package com.kouryoushine.spring.bean.definition;

import com.kouryoushine.thinking.in.spring.ioc.dependency.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.env.MutablePropertySources;

/**
 * @ClassName BeanDefinitionCreatingDemo
 * @Description 两种构建Bean的方式
 * @Author kouryoushine
 * @Date 2021/7/5 23:40
 * @Version 1.0
 */
public class BeanDefinitionCreatingDemo {
    public static void main(String[] args) {
        //1. 通过BeanDefinitioneBuiler构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
         //通过属性设置
        beanDefinitionBuilder.addPropertyValue("name","zhangsan");
        beanDefinitionBuilder.addPropertyValue("id",3);
        //获取BeanDefinition实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //并非最终状态，可以自定义修改

        //2.0 通过AbstractBeanDefinition 以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);

        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("name","lisi");
        propertyValues.add("id",3);
        genericBeanDefinition.setPropertyValues(propertyValues);
    }


}
