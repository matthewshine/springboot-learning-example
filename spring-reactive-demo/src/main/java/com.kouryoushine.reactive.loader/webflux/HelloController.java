package com.kouryoushine.reactive.loader.webflux;


import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/9/29 21:51
 * @Version 1.0
 */
@RestController
public class HelloController {

    @GetMapping("/helloFlux")
    public Mono<String> hello(){
        Mono<String> hello_webFLux = Mono.just("hello webFLux").delayElement(Duration.ofMillis(1000));
        return hello_webFLux;
    }

    @SneakyThrows
    @GetMapping("/helloMVC")
    public String  hello2(){
        Thread.sleep(1000);
        return "helloMVC";
    }
}
