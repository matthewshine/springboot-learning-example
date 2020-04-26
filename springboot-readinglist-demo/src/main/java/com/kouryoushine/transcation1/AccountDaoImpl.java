package com.kouryoushine.transcation1;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AccountDaoImpl implements AccountDao {

    @Resource
    JdbcTemplate jdbcTemplate;



    @Override
    public void outMoney(String account, double money) {
        String sql = "update account set money = money - ? where name =?";
        jdbcTemplate.update(sql,money,account);

    }

    @Override
    public void inMoney(String account, double money) {

        String sql = "update account set money = money + ? where name =?";
        jdbcTemplate.update(sql,money,account);

    }
}
