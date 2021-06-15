package com.mp;

import com.mp.entity.User;
import com.mp.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SimpleTest
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/1/30 22:55
 * @Version 1.0
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class IServiceTest {

    @Resource
    private IUserService userService;

    @Test
    public void testInsert()
    {
        List<User> list = userService.list();
        list.forEach(System.out::println);

    }






}
