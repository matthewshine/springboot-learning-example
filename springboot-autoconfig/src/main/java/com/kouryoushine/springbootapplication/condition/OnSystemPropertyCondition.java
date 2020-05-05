package com.kouryoushine.springbootapplication.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * 系统属性条件判断
 */
public class OnSystemPropertyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        
        //获取注解ConditionalOnSystemProperty的属性和值
        Map<String, Object> annotationAttributes = annotatedTypeMetadata.getAnnotationAttributes(ConditionalOnSystemProperty.class.getName());
        String name = String.valueOf( annotationAttributes.get("name"));
        String value = String.valueOf( annotationAttributes.get("value"));
        String javaPropertyValue = System.getProperty(name);

        //判断注解传入的value值是否等于系统参数

        return value.equals(javaPropertyValue);
    }
}
