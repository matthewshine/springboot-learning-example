package com.kouryoushine.springbootrestdemo.config;

import com.kouryoushine.springbootrestdemo.http.converter.properties.PropertiesHttpMessageConverter;
import com.kouryoushine.springbootrestdemo.web.method.support.PropertiesHandlerMethodArgumentResolver;
import com.kouryoushine.springbootrestdemo.web.method.support.PropertiesHandlerMethodReturnValueHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RestMvcConfig
 * @Description 添加自定义converter
 * @Author kouryoushine
 * @Date 2020/10/8 16:40
 * @Version 1.0
 */
@Configuration
public class RestMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void init(){
        //获取当前RequestMappingHandlerAdapter所有对象
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> newresolver = new ArrayList<>(argumentResolvers.size()+1);
        //添加自定义resolver到首位
        newresolver.add(new PropertiesHandlerMethodArgumentResolver());
        newresolver.addAll(argumentResolvers);

        //重置requestMappingHandlerAdapter
        requestMappingHandlerAdapter.setArgumentResolvers(newresolver);
        System.out.println("requestMappingHandlerAdapter"+requestMappingHandlerAdapter.toString());
         //-------------同理配置handler
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newreturnValueHandlers= new ArrayList<>(returnValueHandlers.size()+1);
        newreturnValueHandlers.add(new PropertiesHandlerMethodReturnValueHandler());
        newreturnValueHandlers.addAll(returnValueHandlers);
        requestMappingHandlerAdapter.setReturnValueHandlers(newreturnValueHandlers);

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.set(0,new PropertiesHttpMessageConverter());//添加到第一个，其他往后排
        //避免将转换器添加到最后，内容匹配是按照顺序的，可能被其他捷足先登
//        converters.add(new PropertiesHttpMessageConverter());
    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        //添加参数处理器到首位
//        //自定义的优先级低于内置，所以这种方式不能够正确添加Resolver
////        resolvers.add(new PropertiesHandlerMethodArgumentResolver());
//    }

    /**
     *  配置跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }
}
