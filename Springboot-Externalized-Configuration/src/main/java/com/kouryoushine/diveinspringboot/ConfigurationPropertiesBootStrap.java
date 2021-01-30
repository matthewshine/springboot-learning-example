package com.kouryoushine.diveinspringboot;

import com.kouryoushine.diveinspringboot.domain.User;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName ConfigurationPropertiesBootStrap
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/10/11 12:26
 * @Version 1.0
 */
@SpringBootApplication
@EnableConfigurationProperties
public class ConfigurationPropertiesBootStrap {

    @Bean
    public User user(){
        return  new User();
    }
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new
                        SpringApplicationBuilder(ConfigurationPropertiesBootStrap.class)
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run(args);
        User user = context.getBean(User.class);
        System.out.println("用户对象 : " + user);
// 关闭上下文
        context.close();
    }
}
