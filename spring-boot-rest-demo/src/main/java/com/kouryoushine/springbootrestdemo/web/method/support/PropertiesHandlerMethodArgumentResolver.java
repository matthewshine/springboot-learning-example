package com.kouryoushine.springbootrestdemo.web.method.support;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @ClassName PropertiesHandlerMethodArgumentResolver
 * @Description  将方法的参数解析到Request请求钟
 * @Author kouryoushine
 * @Date 2020/10/8 17:56
 * @Version 1.0
 */
public class PropertiesHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //判断支持Properties类型的参数
        return Properties.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        //获取http请求
        HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();
        String contentType =request.getHeader("Content-Type");
        Charset charset = MediaType.parseMediaType(contentType).getCharset();
        charset= charset==null?Charset.forName("UTF-8"):charset;

        Properties properties = new Properties();
        //获取输入流
        InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream(),charset);
        properties.load(inputStreamReader);
        return properties;
    }
}
