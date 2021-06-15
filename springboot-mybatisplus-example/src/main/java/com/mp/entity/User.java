package com.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @ClassName User
 * @Description TODO
 * @Author kouryoushine
 * @Date 2021/1/30 22:53
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false )
public class User extends Model<User> {

    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Long managerId;
    private LocalDateTime createTime;

    @TableField(exist = false)
    private String remark;

}
