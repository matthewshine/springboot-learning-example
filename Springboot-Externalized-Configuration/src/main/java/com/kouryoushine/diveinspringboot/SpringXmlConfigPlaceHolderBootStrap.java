package com.kouryoushine.diveinspringboot;

import com.kouryoushine.diveinspringboot.domain.User;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName SpringXmlConfigPlaceHolderBootStrap
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/10/11 0:56
 * @Version 1.0
 */

public class SpringXmlConfigPlaceHolderBootStrap {
    public static void main(String[] args) {
        String [] locations= {"META-INF/spring/spring-context.xml","META-INF/spring/user-context.xml"};

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(locations);
        User user = applicationContext.getBean("user", User.class);
        //打印
        System.out.println(user.toString());
        applicationContext.close();
    }
}
