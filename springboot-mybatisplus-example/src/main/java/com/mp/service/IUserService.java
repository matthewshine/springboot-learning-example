package com.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mp.entity.User;

/**
 * @ClassName IUserService
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/2/2 0:38
 * @Version 1.0
 */

/**
 * IService 是服务层实现的接口，避免我们在服务层写很多代码，使用服务层对象即可操作CRUD
 */

public interface IUserService extends IService<User> {
}
