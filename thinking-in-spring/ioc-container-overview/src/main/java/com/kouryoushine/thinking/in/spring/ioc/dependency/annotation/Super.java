package com.kouryoushine.thinking.in.spring.ioc.dependency.annotation;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * @ClassName Super
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/6/30 23:39
 * @Version 1.0
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Super {

}
