package com.zgw.dao;

import java.sql.ResultSet;

/**
 * UserDao接口
 */
public interface UserDao {

    //注册
    void register(String userName,String passWord);

    //登录
    boolean login(String userName, String passWord);

    //修改密码(用户名，原密码，新密码)
    boolean updatePassword(String userName, String oldPassword,String newPassword);

    //查看全部用户信息
    ResultSet selectAllUser();
}
