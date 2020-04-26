package com.kouryoushine.aopdemo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private  Long id;

    @Datalog(name="产品名称")
    private String name;
    private String category;
    private String detail;
    private BigDecimal buyPrice;
    private  BigDecimal sellPrice;
    private  String provider;
    private Date onlinetime;
    private Date updatetime;


}
