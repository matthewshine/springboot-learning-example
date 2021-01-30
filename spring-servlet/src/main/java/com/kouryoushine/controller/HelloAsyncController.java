//package com.kouryoushine.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.async.DeferredResult;
//
//import java.sql.SQLOutput;
//
///**
// * @ClassName HelloAsyncController
// * @Description 异步控制器
// * @Author kouryoushine
// * @Date 2020/10/9 0:07
// * @Version 1.0
// */
//@RestController
//public class HelloAsyncController {
//    @GetMapping("/hello")
//    public DeferredResult<String> helloworld(){
//        DeferredResult<String> deferredResult= new DeferredResult<>();
//        deferredResult.setResult("hello world");
//        deferredResult.onCompletion(()-> System.out.println("complete"));
//        return deferredResult;
//    }
//}
