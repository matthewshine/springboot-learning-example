package com.kouryoushine.reactive.loader.webflux;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @ClassName SseController
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/9/30 11:11
 * @Version 1.0
 */
@RestController
public class SseController {

    @GetMapping(value = "/sse",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> sendSSE(){
        Flux<String> interval = Flux.interval(Duration.ofSeconds(1)).map((item)->{
         return item +" | "+ LocalDateTime.now();
        });
        return interval;
    }
}
