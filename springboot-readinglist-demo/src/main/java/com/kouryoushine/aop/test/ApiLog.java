package com.kouryoushine.aop.test;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 * 
 * @author bian
 *
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiLog
{
    /**
     * 模块 
     */
    public String title() default "";

    /**
     * 日志记录service实现
     * @return
     */
    public String logService() default "operLogServiceImpl";


    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;

    /**
     * 是否追踪用户操作
     * @return
     */
    public boolean isTrack() default true;
}
