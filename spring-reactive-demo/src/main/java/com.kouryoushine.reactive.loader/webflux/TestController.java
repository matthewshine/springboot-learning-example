package com.kouryoushine.reactive.loader.webflux;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/9/27 23:59
 * @Version 1.0
 */
@RestController
public class TestController {



    @GetMapping("/mvc1")
    private String get(){
        System.out.println("start"+new Date());
        String str = createStr();
        System.out.println("end"+new Date());
        return str;
    }
    @GetMapping("/mono1")
    private Mono<String> get2(){

        System.out.println("start"+new Date());
        Mono<String> stringMono = Mono.fromSupplier(() -> createStr());
        System.out.println("end"+new Date());
        return stringMono;
    }

    @GetMapping(value = "/eventstream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<String> get3(){
        return Flux.fromStream(Arrays.asList("1","2","3","4","5").stream().map((item)-> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return  "data: "+item+" | "+ LocalDateTime.now();
        }));
    }

    private  String createStr(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }
}
