package com.kouryoushine.reactive.loader.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @ClassName WebFluxApplication
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/9/26 19:48
 * @Version 1.0
 */
@SpringBootApplication
@RestController
public class WebFluxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class,args );
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){

        return RouterFunctions.route(serverRequest -> {
          URI uri=  serverRequest.uri();
          return "/hello".equals(uri.getPath());// 判定请求路径
        },serverRequest -> {   //返回请求
            Mono<ServerResponse> result = ServerResponse
                    .status(HttpStatus.OK)
                    .body(Mono.just("Helloworld"),String.class);
            return result;
        });
    }



    @GetMapping("/mvc")
    public String mvc(){
        print("mvc");
        return "MVC";
    }

    @GetMapping("/mono")
    public Mono<String> mono(){
        print("mono");
        return Mono.just("mono");
    }

    public void print(String message){

        System.out.println("[线程+"+Thread.currentThread().getName()+"] "+message);
    }

}
