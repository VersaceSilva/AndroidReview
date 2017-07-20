package com.ncd.greendaotest.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by 80549 on 2017/7/20.
 */
@Entity
public class User {
    @NotNull
    private String name;

    //id 字段必须是Long类型，默认自增
    @Id
    private Long id;

    public User() {
    }

    @Generated(hash = 969448858)
    public User(@NotNull String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
