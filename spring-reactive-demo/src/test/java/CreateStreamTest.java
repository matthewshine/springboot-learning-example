import lombok.SneakyThrows;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CreateStreamTest
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/9/29 17:02
 * @Version 1.0
 */
public class CreateStreamTest {

    @Test
    public void testCreateStream(){
        Flux.just(1,3,3,"3"); //可以重复，可以是不同了类型
        Mono.just(1); //只能包含一个或者没有
        List<String> list = Arrays.asList("1","2","3","4","5");
        Flux<String> stringFlux = Flux.fromStream(list.stream());

        //订阅前什么都不会发生
        //只有subscribe()方法调用的时候才会触发数据流
        stringFlux.subscribe((item)-> System.out.println(item));
        //订阅者的方式2
        Flux.just(1,3,3,5,5,new Exception(),5,8)
                .subscribe(System.out::println,
                        System.out::println,
                        ()-> System.out.println("输出完成")
                        );
    }

    /**
     * 变换操作符map演示
     */

    @Test
    public   void testMap(){
        StepVerifier.create(Flux.range(1, 6)    // 1
                .map(i -> i * i))   // 2
                .expectNext(1, 3, 9, 16, 25, 36)    //3
                .expectComplete()
                .verify()
                ;  // 4
    }

    /**
     * 调度测试
     */
    @SneakyThrows
    @Test
    public void testScheduler(){
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Flux.range(1,100)
                .publishOn(Schedulers.elastic())
                .doOnNext((item)-> System.out.println(Thread.currentThread().getName()+"pub|"+item))
                .subscribeOn(Schedulers.newSingle("单个线程"))
                .publishOn(Schedulers.immediate())
                .doOnNext((item)-> System.out.println(Thread.currentThread().getName()+"sub|"+item))
                .subscribe(System.out::println, null, countDownLatch::countDown);
        countDownLatch.await(10, TimeUnit.SECONDS);
    }


    /**
     * 异常处理
     */

    @Test
    public void testException(){
        Flux.range(1,10)
                .map(x->x/(3-x))
                .subscribe(System.out::println,(e)->{System.err.println(e.getMessage());},()->System.out.println("complete"));

        Flux.range(1,10)
                .map(x->x/(3-x))
                .doOnNext(System.out::println) //正常打印
//                .onErrorResume((item)->Flux.just(33)) //错误时替换为33
                .doOnError(System.err::println) //错误时打印
                .doOnComplete(()->System.out.println("complete2"))
                .subscribe();

    }

    @Test
    public void testBackPressure(){
        Flux.range(1,10)
                .subscribe(new Subscriber<Integer>() {
                    Subscription subscription;
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        //订阅时触发
                        this.subscription= subscription;
                        subscription.request(1);//请求一个元素

                    }

                    @Override
                    public void onNext(Integer integer) {
                    //获取到元素时触发
                        System.out.println("当前元素："+integer+""+new Date());
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscription.request(2);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("on error");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("complete");
                    }
                });
    }

    /**
     * 测试和验证方法
     * @return
     */

    public Flux<Integer> generateFluxFrom1To6() {
        return Flux.just(1, 2, 3, 4, 5, 6);
    }
    private Mono<Integer> generateMonoWithError() {
        return Mono.error(new Exception("some error"));
    }
    @Test
    public void testViaStepVerifier() {
        StepVerifier.create(generateFluxFrom1To6())
                .expectNext(1, 4, 3, 4, 5, 6)
                .expectComplete()
                .verify();
        StepVerifier.create(generateMonoWithError())
                .expectErrorMessage("some error")
                .verify();
    }

}
