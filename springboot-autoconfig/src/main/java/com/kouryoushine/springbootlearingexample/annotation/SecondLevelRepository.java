package com.kouryoushine.springbootlearingexample.annotation;

import java.lang.annotation.*;

/**
 * 层次性：一级的注解可以用来标注二级注解
 * 派生性： 每层注解集成上级注解的特性
 */


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@FirstLevelRepository
public @interface SecondLevelRepository {
    String value() default "";
}