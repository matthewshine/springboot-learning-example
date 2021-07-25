package com.kouryoushine.ioc.demo.beans;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/**
 * @ClassName BeanInfoDemo
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/6/28 23:37
 * @Version 1.0
 */
public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class,Object.class);

        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertity->{
                    System.out.println(propertity);
                });
    }



    //    转换bean属性，String-Integer
    @Test
    public void test() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class,Object.class);

        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertity->{
                    String propertityName = propertity.getName();
                    if("age".equals(propertityName)){
                        propertity.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                        propertity.createPropertyEditor(propertity);
                    }
                    System.out.println(propertity);
                });
    }

    static  class StringToIntegerPropertyEditor extends PropertyEditorSupport{
        public void setAsText(String text) throws java.lang.IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }
    }

}
