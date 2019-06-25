package com.neuedu.service;

import com.neuedu.common.ServletResponse;
import com.neuedu.pojo.User;

import javax.servlet.http.HttpSession;

public interface UserService {

    //登录功能
    ServletResponse login(String name, String psw, HttpSession session);

    //注册功能
    ServletResponse register(User user);

    //获取用户登录信息
    ServletResponse getUserInfo(HttpSession session);

    //忘记密码。1.获取密保问题
    ServletResponse forgetPsw_Ques(String username);

    //忘记密码。2.提交问题答案
    ServletResponse forgetPsw_Answer(String username, String question, String answer);

    //忘记密码。3.重置密码
    ServletResponse forgetPsw_ResetPsw(String username, String passwordNew, String forgetToken);

    //登录状态中重置密码
    ServletResponse reset_password(HttpSession session, String passwordOld,String passwordNew);

    //登录状态更新个人信息
    ServletResponse update_information(HttpSession session,User user);

    //退出登录
    ServletResponse logout(HttpSession session);

}
