package com.kouryoushine.transcation1;


public interface AccountDao {


    //转出和转入的账号金额
    public void  outMoney(String account, double money);
    public void  inMoney(String account, double money);
}
