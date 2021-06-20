package com.kouryoushine.mybatis.demo.entity;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName User
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/1/30 22:53
 * @Version 1.0
 */
@Data

public class User {


    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Long managerId;
    private LocalDateTime createTime;


    private String remark;

}
