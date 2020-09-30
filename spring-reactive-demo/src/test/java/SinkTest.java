import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SinkTest
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/9/30 1:31
 * @Version 1.0
 */
@Slf4j
public class SinkTest {

    @Test
    public void sinkTest1(){
        Flux.generate(()->2,(index,sink)->{
            sink.next(index+" | "+new Date());
            if(index==5){
                sink.complete();
            }
            return index+1;
        },(item)-> System.out.println("complete = finally"+item))
         .doOnNext(System.out::print)
        .subscribe();
    }

    @SneakyThrows
    @Test
    public void fluxSinkTest(){
        final Flux<Integer> flux = Flux.<Integer> create(fluxSink -> {
            //NOTE sink:class reactor.core.publisher.FluxCreate$SerializedSink
            log.info("sink:{}",fluxSink.getClass());
            while (true) {
                log.info("sink next");
                fluxSink.next(ThreadLocalRandom.current().nextInt());
            }
        }, FluxSink.OverflowStrategy.BUFFER);

        //NOTE flux:class reactor.core.publisher.FluxCreate,prefetch:-1
        log.info("flux:{},prefetch:{}",flux.getClass(),flux.getPrefetch());

        flux.subscribe(e -> {
            log.info("subscribe:{}",e);
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        flux.just(1,3,34);

        TimeUnit.MINUTES.sleep(1);
    }
}
