package com.kouryoushine.diveinspringboot.domain;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName User
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/10/11 0:51
 * @Version 1.0
 */
@ConfigurationProperties("user")
public class User {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
