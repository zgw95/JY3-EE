package com.neuedu.controller.back;

import com.neuedu.common.ServletResponse;
import com.neuedu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/user")
public class AdminCon {

    @Autowired
    private AdminService as;

    /**
     * 管理员登录
     */
    @ResponseBody
    @RequestMapping("/login.do")
    public ServletResponse adminLogin(String name, String psw, HttpSession session)
    {
        return as.login(name,psw,session);
    }
}
