package com.kouryoushine.thinking.in.spring.ioc.dependency.repository;

import com.kouryoushine.thinking.in.spring.ioc.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * @ClassName UserRepository
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/7/2 0:00
 * @Version 1.0
 */
public class UserRepository {
    //自定义bean
    private Collection<User> users;
    //内建的非Bean对象
    private BeanFactory beanFactory;

    private ObjectFactory<User> objectFactory;
    private ObjectFactory<ApplicationContext> objectFactoryc;
    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public ObjectFactory<User> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<User> objectFactory) {
        this.objectFactory = objectFactory;
    }

    public ObjectFactory<ApplicationContext> getObjectFactoryc() {
        return objectFactoryc;
    }

    public void setObjectFactoryc(ObjectFactory<ApplicationContext> objectFactoryc) {
        this.objectFactoryc = objectFactoryc;
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "users=" + users +
                '}';
    }
}
