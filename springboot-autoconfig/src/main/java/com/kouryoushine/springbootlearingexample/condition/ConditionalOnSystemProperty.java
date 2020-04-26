package com.kouryoushine.springbootlearingexample.condition;

/**
 * java系统属性判断
 */

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional({OnSystemPropertyCondition.class})
public @interface ConditionalOnSystemProperty {

    String name();  //系统参数名
    String value(); //参数值
}
