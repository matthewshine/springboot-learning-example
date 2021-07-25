package com.kouryoushine.thinking.in.spring.ioc.dependency.domain;

import com.kouryoushine.thinking.in.spring.ioc.dependency.annotation.Super;

/**
 * @ClassName SuperUser
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/6/30 23:38
 * @Version 1.0
 */
@Super
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                ", id=" + id +
                '}';
    }
}
