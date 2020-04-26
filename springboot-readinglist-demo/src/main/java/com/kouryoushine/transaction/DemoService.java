package com.kouryoushine.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DemoService {

    @Autowired
    UserDao userDao;

    @Autowired
    OperationDao operationDao;

    //添加事务注解后就会保证两条同时执行
    @Transactional
    public void addUser(String name){
        OperationLog operationLog = new OperationLog();
        operationLog.setContent("crate user:"+name);
        operationDao.save(operationLog); //保存日志

        User user = new User();
        user.setName(name);
        userDao.save(user);

    }
}
