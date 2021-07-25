package com.kouryoushine.spring.bean.definition;

import com.kouryoushine.spring.bean.factory.DefaultUserFactory;
import com.kouryoushine.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @ClassName BeanInitializationDemo
 * @Description Bean初始化探索
 * @Author kouryoushine
 * @Date 2021/7/7 0:47
 * @Version 1.0
 */
@Configuration
public class BeanInitializationDemo {
    public static void main(String[] args) {
        //创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        //启动spring上下文
        applicationContext.refresh();
        //非延时加载，在spring上下文启动时初始化
        System.out.println("spring context 已启动");
        //延时加载，在spring上下文启动时不会初始化，在依赖查找时初始化
        //依赖查找
        UserFactory bean = applicationContext.getBean(UserFactory.class);
        System.out.println(bean);

        //关闭spring上下文
        applicationContext.close();



    }

    @Bean(initMethod = "initUserFactory",destroyMethod = "doDestory")
//    @Lazy
    public UserFactory userFactory(){
       return new DefaultUserFactory();
    }

}
