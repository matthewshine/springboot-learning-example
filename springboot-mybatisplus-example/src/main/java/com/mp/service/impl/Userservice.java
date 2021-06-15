package com.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mp.dao.UserMapper;
import com.mp.entity.User;
import com.mp.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName Userservice
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/2/2 0:39
 * @Version 1.0
 */
@Service
public class Userservice extends ServiceImpl<UserMapper, User> implements IUserService {
}
