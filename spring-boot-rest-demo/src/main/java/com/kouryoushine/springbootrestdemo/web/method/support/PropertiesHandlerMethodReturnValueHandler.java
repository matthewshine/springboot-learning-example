package com.kouryoushine.springbootrestdemo.web.method.support;

import com.kouryoushine.springbootrestdemo.http.converter.properties.PropertiesHttpMessageConverter;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @ClassName PropertiesHandlerMethodReturnValueHandler
 * @Description 返回值解析器，不依赖@ResponseBody自己实现解析器
 * @Author kouryoushine
 * @Date 2020/10/8 19:00
 * @Version 1.0
 */
public class PropertiesHandlerMethodReturnValueHandler  implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        //判断方法返回类型是否和Properties一致
        return Properties.class.equals(returnType.getMethod().getReturnType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        //获取http请求
        ServletWebRequest servletWebRequest=  (ServletWebRequest) webRequest;
        HttpServletRequest request = servletWebRequest.getRequest();
        String contentType =request.getHeader("Content-Type");
        MediaType mediaType = MediaType.parseMediaType(contentType);

        Properties properties =  (Properties)returnValue;
        HttpServletResponse response = servletWebRequest.getResponse();
        HttpOutputMessage httpOutputMessage = new ServletServerHttpResponse(response);
        //复用httpMessageConverter实现内容输出
        PropertiesHttpMessageConverter httpMessageConverter = new PropertiesHttpMessageConverter();
        //输出
        httpMessageConverter.write(properties,mediaType,httpOutputMessage);
        // 告知 Spring Web MVC 当前请求已经处理完毕
        mavContainer.setRequestHandled(true);
    }
}
