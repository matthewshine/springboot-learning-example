package com.kouryoushine.reactive.loader.reactor;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.*;
import java.util.concurrent.SubmissionPublisher;

/**
 * @ClassName FlowDemo
 * @Description 利用jdk9中的FlowAPI演示
 * @Author kouryoushine
 * @Date 2020/9/27 22:39
 * @Version 1.0
 */
public class FlowDemo {

    public static void main(String[] args) throws InterruptedException {
        //1, 定义发布者
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher();
        //2, 定义订阅者
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<Integer>() {
            //控制器，订阅关系的合约
            Subscription subscription;
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                //建立订阅关系时调用
                this.subscription=subscription;
                subscription.request(10);//获取元素
            }

            @Override
            public void onNext(Integer item) {
                //获取到元素时执行
                System.out.println("当前元素: "+item);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscription.request(10);//获取元素数量
            }

            @Override
            public void onError(Throwable throwable) {
                //处理元素时抛出异常
                System.out.println("处理异常: "+throwable.getMessage());
                subscription.cancel();//停止生产

            }

            @Override
            public void onComplete() {
                //处理结束时执行
                System.out.println("complete");
            }
        };

        // 绑定发布者和订阅者
        publisher.subscribe(subscriber);

        // 生产数据
        for (int i = 0; i <1000 ; i++) {
            publisher.submit(i);
            //submit是阻塞方法，只要生产者缓冲池满了，就会阻塞等待缓冲池中元素被消耗
            System.out.println("生产数据： "+i);
        }
        publisher.close();
        Thread.sleep(100000);
    }
}
