package com.kouryoushine.diveinspringboot;

import com.kouryoushine.diveinspringboot.domain.User;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @ClassName XmlPlaceholderExternalizedConfigurationBootstrap
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/10/11 1:09
 * @Version 1.0
 */
@ImportResource("META-INF/spring/user-context.xml") // 加载 Spring 上下文 XML 文件
@EnableAutoConfiguration
public class XmlPlaceholderExternalizedConfigurationBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new
                        SpringApplicationBuilder(XmlPlaceholderExternalizedConfigurationBootstrap.class)
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run(args);
        User user = context.getBean("user", User.class);
        System.out.println("用户对象 : " + user);
// 关闭上下文
        context.close();
    }
}
