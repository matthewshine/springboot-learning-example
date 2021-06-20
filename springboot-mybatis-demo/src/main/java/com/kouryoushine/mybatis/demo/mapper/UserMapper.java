package com.kouryoushine.mybatis.demo.mapper;

import com.kouryoushine.mybatis.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
//@Mapper
public interface UserMapper {
	//查询所有用户信息
    List<User> getAllUser();
}