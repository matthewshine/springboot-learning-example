package com.kouryoushine.spring.bean.factory;

import com.kouryoushine.thinking.in.spring.ioc.dependency.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @ClassName UserFactoryBean
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/7/6 23:56
 * @Version 1.0
 */
public class UserFactoryBean implements FactoryBean {
    public Object getObject() throws Exception {
        return User.createUser();
    }

    public Class<?> getObjectType() {
        return User.class;
    }
}
