package com.zgw.dao;

import com.zgw.pojo.User;

import java.util.List;

public interface UserDao {
    //查询全部用户
    List<User> getAllUser();

    //删除指定用户byID
    void deleteUser(String num);

    //修改指定用户byID
    void  updateUser(User user);
}
