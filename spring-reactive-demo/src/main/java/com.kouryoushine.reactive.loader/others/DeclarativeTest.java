package com.kouryoushine.reactive.loader.others;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName DeclarativeTest
 * @Description 声明式编程和命令式编程对比
 * @Author kouryoushine
 * @Date 2020/9/29 11:13
 * @Version 1.0
 */

public class DeclarativeTest {

    @Data
    class User{
        private String name;
        private Integer age;

        public User(String zhangsan, int i) {
        }
    }

    /**
     * 获取年龄大于30岁的人
     * @param args
     */
    public void test(String[] args) {
        List<String> name = new ArrayList<>();
        List<User> userList = Arrays.asList(new User("zhangsan",33),new User("wangwu",33));
        for (User user : userList) {
            if(user.getAge()>30){
                name.add(user.getName());
            }
        }
    }
//    声明式
//    SELECT * FROM users where  age >30

}
