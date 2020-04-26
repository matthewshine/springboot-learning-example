package com.kouryoushine.config;

import com.kouryoushine.readinglist.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    @Autowired
    private ReaderRepository readerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .antMatchers("/").access("hasRole(Reader)") //拦截所有请求验证角色
               .antMatchers("/**").permitAll()//通过所有静态资源
           .and()
               .formLogin()
               .loginPage("/login")//指定登录页面
               .failureUrl("/login?error=true"); //验证失败跳转页面

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() { //自定义UserDetail类
            @Override
            public UserDetails loadUserByUsername(String username)
                    throws UsernameNotFoundException {
                UserDetails userDetails;
                userDetails = readerRepository.findById(username).get();
                if (userDetails != null) {
                    return userDetails;
                }
                throw new UsernameNotFoundException("User '" + username + "' not found.");
            }
        });
    }
}
