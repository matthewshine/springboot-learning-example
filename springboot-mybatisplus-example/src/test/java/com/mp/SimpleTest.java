package com.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mp.dao.UserMapper;
import com.mp.entity.User;
import org.junit.Assert;
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
public class SimpleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect()
    {
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testInsert()
    {
        User user = new User();
        user.setAge(33);
        user.setName("mnamewer");
        userMapper.insert(user);

    }

    @Test
    public void testWrapper1(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 姓名包含雨，年两小于40岁
        queryWrapper.like("name","雨")
                .lt("age",40)
                .or().ge("age",25).orderByDesc("age");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testWrapper2(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // SELECT id,name,age,email,manager_id,create_time FROM user WHERE date_format(create_time,'%Y-%m-%d')=? AND manager_id IN (select id from user where name like '王%')
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}","2019-02-14")
                .inSql("manager_id","select id from user where name like '王%'");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testWrapper3(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //and()可以理解为括号
        queryWrapper.likeRight("name","王")
                .and(wq->wq.lt("age",40))
                .or()
                .isNotNull("email");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }


    /**
     * 查询部分字段
     */
    @Test
    public void testWrapper4(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //and()可以理解为括号
        queryWrapper.select("name","age").likeRight("name","王")
                .and(wq->wq.lt("age",40))
                .or()
                .isNotNull("email");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * Condition 满足后才执行sql语句
     */
    @Test
    public void testCondition(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //ddd不为空的时候才执行
        queryWrapper.select("name","age").likeRight(StringUtils.isNotBlank("dd "),"name","王")
                .and(wq->wq.lt("age",40));

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * Entity实体接收参数后直接查询
     */
    @Test
    public void testWrapperEntity(){
        User user = new User();
        user.setName("李艺伟");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        //ddd不为空的时候才执行

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 返回maps，只返回指定列的内容
     */
    @Test
    public void testSelectmaps(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //ddd不为空的时候才执行
        queryWrapper.select("name","age")
                .and(wq->wq.lt("age",40));
        //ddd不为空的时候才执行

        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
    }

    @Test
    public void testlambda(){
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        //ddd不为空的时候才执行
        LambdaQueryWrapper<User> queryWrapper = userLambdaQueryWrapper.like(User::getName, "王").lt(User::getAge, 40);
        //ddd不为空的时候才执行
        userMapper.selectMaps(queryWrapper);
    }

    /**
     * 测试分页
     */
    @Test
    public void selectPage(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.ge("age",26);
        //分页参数
        Page<User> page = new Page<>(1,2);
        IPage<User> userIPage = userMapper.selectPage(page, queryWrapper);
        //通过IPage可以获取分页信息
        System.out.println(userIPage.getPages());
        System.out.println(userIPage.getRecords());
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete(){
        Map<String,Object> map = new HashMap<>();
        map.put("age",31);
        //删除age=31的数据
        userMapper.deleteByMap(map);
    }
}
