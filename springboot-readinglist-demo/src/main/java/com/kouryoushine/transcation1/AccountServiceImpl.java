package com.kouryoushine.transcation1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;
    @Resource
    TransactionTemplate transactionTemplate;
//
//    @Override
//    public void transfer(String out, String in, double money) {
//
//        transactionTemplate.execute(new TransactionCallback<Object>() {
//
//            @Override
//            public Object doInTransaction(TransactionStatus transactionStatus) {
//                accountDao.inMoney(in,money);
////                int i =1/0;
//                accountDao.outMoney(out,money);
//                return null;
//            }
//        });
//    }

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public void transfer(String out, String in, double money) {
        accountDao.inMoney(in,money);
//                int i =1/0;
        accountDao.outMoney(out,money);
    }


}
