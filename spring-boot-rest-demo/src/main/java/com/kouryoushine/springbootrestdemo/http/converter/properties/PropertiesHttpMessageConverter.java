package com.kouryoushine.springbootrestdemo.http.converter.properties;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @ClassName PropertiesHttpMessageConverter
 * @Description  Properteis对象的HttpMessageConverter实现
 * @Author kouryoushine
 * @Date 2020/10/8 16:21
 * @Version 1.0
 */
public class PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter <Properties>{

    public PropertiesHttpMessageConverter(){
        //设置support mediatype为text/properties
        super(new MediaType("text","properties"));
    }

    //从Properties
    @Override
    protected void writeInternal(Properties properties, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        HttpHeaders headers = outputMessage.getHeaders();

        //获取媒体类型
        MediaType contentType = headers.getContentType();
        Charset charset = contentType.getCharset();
        //如果charset为空就设置为默认值UTF-8
        charset= charset==null?Charset.forName("UTF-8"):charset;
        Writer writer = new OutputStreamWriter(outputMessage.getBody(),charset);
        properties.store(writer,"comments not thing userful");
    }

    //从inputmessage转换为Properties对象
    @Override
    protected Properties readInternal(Class<? extends Properties> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        HttpHeaders headers = inputMessage.getHeaders();

        //获取媒体类型
        MediaType contentType = headers.getContentType();
        Charset charset = contentType.getCharset();
        //如果charset为空就设置为默认值UTF-8
        charset= charset==null?Charset.forName("UTF-8"):charset;

        Properties properties = new Properties();
        //获取输入流
        InputStream inputStream = inputMessage.getBody();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,charset);
        properties.load(inputStreamReader);
        return properties;
    }

    @Override
    public Properties read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(null,inputMessage);
    }
}
