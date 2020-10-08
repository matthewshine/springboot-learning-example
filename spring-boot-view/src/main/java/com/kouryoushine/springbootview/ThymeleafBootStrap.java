package com.kouryoushine.springbootview;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;


/**
 * @ClassName ThymeleafBootStrap
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/10/6 22:14
 * @Version 1.0
 */
public class ThymeleafBootStrap {
    public static void main(String[] args) {

        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        Context context= new Context();
        context.setVariable("message","hellowrold");

        String content = "<p th:text=\"${message}\">!!!</p>";
        String result =springTemplateEngine.process(content,context);
        System.out.println(result);
    }
}
