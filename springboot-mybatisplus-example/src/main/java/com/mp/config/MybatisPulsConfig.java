package com.mp.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisPulsConfig
 * @Description 配置分页
 * @Author kouryoushine
 * @Date 2021/1/31 23:20
 * @Version 1.0
 */
@Configuration
public class MybatisPulsConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return  new PaginationInterceptor();
    }
}
