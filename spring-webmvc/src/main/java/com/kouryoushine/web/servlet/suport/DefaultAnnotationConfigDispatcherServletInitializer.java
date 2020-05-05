package com.kouryoushine.web.servlet.suport;

import com.kouryoushine.web.config.DispatherServletConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DefaultAnnotationConfigDispatcherServletInitializer
extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() { //web.xml
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() { //构建DispatcherServlet
        return new Class[]{DispatherServletConfig.class}; //导入类
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
