package com.kouryoushine.thinking.in.spring.ioc.dependency.domain;

/**
 * @ClassName User
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/6/30 23:08
 * @Version 1.0
 */
public class User {
    long id;
    private String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    public static User createUser(){
      return   new User();
    }

}
