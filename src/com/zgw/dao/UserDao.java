package com.zgw.dao;

import com.zgw.pojo.User;

public interface UserDao {

    //注册
    void register(User user);

    //登录
    boolean login(String userName, String passWord);






}
