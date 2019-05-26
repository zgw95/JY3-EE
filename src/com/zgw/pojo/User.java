package com.zgw.pojo;

import java.sql.Date;

/**
 * 用户类
 */
public class User {
    private String id;
    private String name;
    private String password;
    private Date date;

    public User() {
    }

    public User(String id, String name, String password, Date date) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
