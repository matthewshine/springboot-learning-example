package com.kouryoushine.readinglist;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

/**
 * reader实现了UserDetail的方法，这样Reader类就可以代表Security的用户进行权限认证
 */

@Entity
public class Reader implements UserDetails {

    private static final long serialVersionUID = 1L;
    @Id //表明这是实体类的id
    private String username;
    private String fullname;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("READER")); //全部授予READER权限
    }

    @Override
    public boolean isAccountNonExpired() { //不过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //不加锁
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //不撤销
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
