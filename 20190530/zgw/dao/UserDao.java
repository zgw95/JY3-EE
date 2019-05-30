package com.zgw.dao;

import com.zgw.pojo.User;

import java.util.List;

public interface UserDao {

    //注册
//    void register(User user);

    //登录
    boolean login(String userName, String passWord);

    //检查用户名是否存在
    boolean checkUserName(String userName);

    //查询全部用户信息
    List<User> queryALl();






}
