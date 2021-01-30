package com.kouryoushine.diveinspringboot;

import com.kouryoushine.diveinspringboot.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;

/**
 * @ClassName XmlPlaceholderExternalizedConfigurationBootstrap
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/10/11 1:09
 * @Version 1.0
 */

@EnableAutoConfiguration
@EnableConfigurationProperties
public class EnvironmentConfigurationBootstrap implements BeanFactoryAware {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new
                        SpringApplicationBuilder(EnvironmentConfigurationBootstrap.class)
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run(args);
        User user = context.getBean("user1", User.class);
        System.out.println("用户对象 : " + user);
// 关闭上下文
        context.close();
    }

    @Autowired
    private Environment environment;

    @Bean
    public User user1(Environment environment){
        Long id = environment.getProperty("user.id", Long.class);
        String name = environment.getProperty("user.name");
        System.out.println(id);
        User user = new User();
        user.setId(id);
        user.setName(name);
        return  user;
    }

    /**
     * 通过beanFactory获取Environment
     * @param beanFactory
     * @throws BeansException
     */
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
             this.environment=beanFactory.getBean(Environment.class);

    }
}
