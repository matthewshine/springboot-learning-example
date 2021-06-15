package com.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mp.dao.UserMapper;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SimpleTest
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/1/30 22:55
 * @Version 1.0
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class ARTest {

    @Test
    public void testInsert()
    {
        //无需使用Mapper直接使用对象操作即可
        User user = new User();
        user.setName("new person");
        user.setAge(39);
        user.insert();
        System.out.println(user.getId());

    }

    @Test
    public void testSelect()
    {
        //无需使用Mapper直接使用对象操作即可
        User user = new User();
        User user1 = user.selectById(1088248166370832385l);
        System.out.println(user1);

    }

    @Test
    public void testUpdate()
    {
        //无需使用Mapper直接使用对象操作即可
        User user = new User();
        user.setId(1088248166370832385l);
        user.setEmail("test update");
        boolean b = user.updateById();

        System.out.println(b);

    }




}
