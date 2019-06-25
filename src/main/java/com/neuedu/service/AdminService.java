package com.neuedu.service;

import com.neuedu.common.ServletResponse;

import javax.servlet.http.HttpSession;

public interface AdminService {

    //管理员登录
    ServletResponse login(String name, String psw, HttpSession session);
}
