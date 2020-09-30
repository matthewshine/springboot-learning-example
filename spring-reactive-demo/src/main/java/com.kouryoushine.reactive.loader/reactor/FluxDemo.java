package com.kouryoushine.reactive.loader.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @ClassName FluxDemo
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/9/26 13:53
 * @Version 1.0
 */
public class FluxDemo {

    public static void main(String[] args) throws InterruptedException {
        Flux.just("A","B","C") //发布数据
           .subscribe(new Subscriber<String>() {

               Subscription subscription;
               int count=0;
               @Override
               public void onSubscribe(Subscription subscription) {
                        this.subscription=subscription;
                        subscription.request(1);

               }

               @Override
               public void onNext(String s) {
                   System.out.println("元素"+s);
                   subscription.request(1);
                   count++;
                   if(count==2){
                       throw  new RuntimeException("自定义异常");
                   }
               }

               @Override
               public void onError(Throwable throwable) {
                        println(throwable.getMessage());
               }

               @Override
               public void onComplete() {
                    println("完成");
               }
           });
        //调度执行
        Thread.sleep(1000);

    }

//    public static void main(String[] args) throws InterruptedException {
//        Flux.just("A","B","C") //发布数据
//                .map((value)->"prefix+"+value)
//                .publishOn(Schedulers.elastic())//线程调度
//                .subscribe(FluxDemo::println, //数据消费 = onNext(T)
//                        FluxDemo::println, //异常抛出 = onError(Throwable)
//                        ()->{
//                            System.out.println("回调完成！！！");//回调 = onComplete
//                        },
//                        subscription -> { //背压  = onSubcribe(Subcription)
//                            subscription.request(2); //限制获取元素的个数为2 =
//                        }
//                );
//        //调度执行
//        Thread.sleep(1000);
//
//    }

    private static void println(Object object){
        System.out.println(Thread.currentThread().getName()+" | "+object);

    }
}
