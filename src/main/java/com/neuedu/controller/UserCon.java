package com.neuedu.controller;

import com.neuedu.common.ServletResponse;
import com.neuedu.pojo.User;
import com.neuedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/portal/user")
public class UserCon {

    @Autowired
    private UserService us;

    //登录
    @ResponseBody
    @RequestMapping("/login.do")
    public ServletResponse login(String name, String psw, HttpSession session)
    {
        return us.login(name,psw,session);
    }

    //注册
    @ResponseBody
    @RequestMapping("/register.do")
    public ServletResponse register(User user)
    {
        return us.register(user);
    }

    //获取登录用户信息
    @ResponseBody
    @RequestMapping("/get_user_info.do")
    public ServletResponse getUserInfo(HttpSession session)
    {
        return us.getUserInfo(session);
    }

    //忘记密码
    //1.获取密保问题
    @ResponseBody
    @RequestMapping("/forget_get_question.do")
    public ServletResponse forget_get_question(String username)
    {
        return us.forgetPsw_Ques(username);
    }

    //2.提交问题答案
    @ResponseBody
    @RequestMapping("/forget_check_answer.do")
    public ServletResponse forget_check_answer(String username, String question, String answer)
    {
        return us.forgetPsw_Answer(username,question,answer);
    }

    //3.重置密码
    @ResponseBody
    @RequestMapping("/forget_reset_password.do")
    public ServletResponse forget_reset_password(String username, String passwordNew, String forgetToken)
    {
        return us.forgetPsw_ResetPsw(username,passwordNew,forgetToken);
    }

    //登录状态中重置密码
    @ResponseBody
    @RequestMapping("/reset_password.do")
    public ServletResponse reset_password(HttpSession session,String passwordOld,String passwordNew)
    {
        return us.reset_password(session,passwordOld,passwordNew);
    }

    //登录状态更新个人信息
    @ResponseBody
    @RequestMapping("/update_information.do")
    public ServletResponse update_information(HttpSession session,User user)
    {
        return us.update_information(session,user);
    }

    //退出登录
    @ResponseBody
    @RequestMapping("/logout.do")
    public ServletResponse logout(HttpSession session)
    {
        return us.logout(session);
    }


}
