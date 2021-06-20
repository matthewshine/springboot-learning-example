package com.mp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mp.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/1/30 22:54
 * @Version 1.0
 */
@Mapper
public interface UserMapper  extends BaseMapper<User> {

}
