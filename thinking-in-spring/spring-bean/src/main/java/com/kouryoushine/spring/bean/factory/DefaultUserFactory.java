package com.kouryoushine.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @ClassName DefaultUserFactory
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/7/7 0:07
 * @Version 1.0
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void init(){
        System.out.println("@postconstruct :UserFactory初始化中。。。");
    }

    public void initUserFactory(){
        System.out.println("自定义初始化 :UserFactory初始化中。。。");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean-afterPropertiesSet初始化 :UserFactory初始化中。。。");
    }


    /**
     *   销毁方式一：@PreDestroy注解
     */
    @PreDestroy
    public void preDestory(){
        System.out.println("@PreDestroy bean销毁中");
}
    /**
     *     销毁方式二：实现DisposableBean
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("@实现DisposableBean bean销毁中");
    }
    /**
     *     销毁方式三：自定义方法
     */
    public void doDestory(){
        System.out.println("自定义方法 bean销毁中");
    }

    @Override
    public void finalize(){
        System.out.println("当前bean正在被回收");
    }
}
