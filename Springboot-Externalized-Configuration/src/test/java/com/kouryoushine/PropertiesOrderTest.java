package com.kouryoushine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName PropertiesOrderTest
 * @Description PropertySource Order Test
 * @Author kouryoushine
 * @Date 2020/10/11 16:03
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@TestPropertySource(properties = "user.id=8")
@SpringBootTest(properties = "user.id=9",classes = PropertiesOrderTest.class,
webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PropertiesOrderTest {

    @Value("${user.id}")
    private Long userid;

    @Test
    public void test(){
        System.out.println(userid);
    }
}
