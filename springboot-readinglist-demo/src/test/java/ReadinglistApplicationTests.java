import com.kouryoushine.aop.security.CurrentUserHolder;
import com.kouryoushine.aop.service.ObjectTest;
import com.kouryoushine.aop.service.ProductService;
import com.kouryoushine.aop.test.TestServcie;
import com.kouryoushine.transaction.DemoService;
import com.kouryoushine.transcation1.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ReadinglistApplicationTests {

    @Autowired
    ProductService productService;

    @Test
    void test1() {
        CurrentUserHolder.set("admin");
        productService.update();
    }



    @Test
    void test2() {
        CurrentUserHolder.set("tom");
        productService.update(); //非admin就会被阻拦
    }

    @Autowired
    ObjectTest objectTest;

    @Test
    void test3() {
        objectTest.test1();
        objectTest.test2(3l);
    }

    @Test
    void test4() {
        productService.update();
        productService.test();
    }

    @Autowired
    DemoService demoService;

    @Test
    void  test5(){
//        demoService.addUser("zhagddnsan");
        demoService.addUser("wangw");
        demoService.addUser("wangw");
    }

    @Autowired
    TestServcie testServcie;
    @Test
    void  test6() throws Exception{

        testServcie.save("参数1字符串",33);
    }

    @Autowired
    AccountService accountService;

    @Test
    void  test7(){
        accountService.transfer("aaa","bbb",800);
    }

}
