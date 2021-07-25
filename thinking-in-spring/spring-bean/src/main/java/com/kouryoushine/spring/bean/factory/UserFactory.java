package com.kouryoushine.spring.bean.factory;

import com.kouryoushine.thinking.in.spring.ioc.dependency.domain.User;

/**
 * @ClassName UserFactory
 * @Description User工厂类
 * @Author kouryoushine
 * @Date 2021/7/6 23:46
 * @Version 1.0
 */
public interface UserFactory {

   default User createUser(){
        return User.createUser();
    }
}
