package com.kouryoushine.springbootdemo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @ClassName WebMvcConfig
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/10/11 23:43
 * @Version 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig=new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullListAsEmpty,
//                SerializerFeature.WriteMapNullValue, //map转
//                SerializerFeature.WriteNullStringAsEmpty,//字符串null返回空字符串
//                SerializerFeature.WriteNullBooleanAsFalse, //布尔值
//                SerializerFeature.WriteNullNumberAsZero, //数字转0
//                SerializerFeature.WriteDateUseDateFormat, //日期
//                SerializerFeature.PrettyFormat);
//        converter.setFastJsonConfig(fastJsonConfig);
//        converters.add(0,converter);
//    }

//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(0, new MappingJackson2HttpMessageConverter(){
//            @Override
//            public ObjectMapper getObjectMapper() {
//                super.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
//                return super.getObjectMapper();
//            }
//        });
//    }


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0,new JacksonHttpMessageConverter());
    }
}
