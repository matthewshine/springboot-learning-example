package com.kouryoushine.transaction;

import javax.persistence.*;
import java.util.Date;

@Entity
public class OperationLog {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private  String content;

    private Date createAt;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    //持久化前执行
    @PrePersist
    public void settingTime(){
        createAt = new Date();
    }
}
