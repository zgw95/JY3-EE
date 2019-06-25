package com.neuedu.service;

import com.neuedu.common.Const;
import com.neuedu.common.ServletResponse;
import com.neuedu.dao.UserMapper;
import com.neuedu.pojo.User;
import com.neuedu.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper um;

    //管理员登录
    @Override
    public ServletResponse login(String name, String psw, HttpSession session) {
        if (name == null || name.length() == 0)
        {
            return ServletResponse.getRequestFailed("用户名不能为空!");
        }
        if (psw == null || psw.length() == 0)
        {
            return ServletResponse.getRequestFailed("密码不能为空!");
        }
        int i = um.selectUserByUserName(name);
        if (i == 0)
        {
            return ServletResponse.getRequestFailed("用户名不存在，请重新输入");
        }
        //密码加密
        String md5Encode = MD5Util.MD5Encode(psw, null);
        User user = um.selectUserInfoByUserNameAndPsw(name, md5Encode);
        if (user != null)
        {
            if (user.getRole().intValue() == Const.COMMONADMIN)
            {
                session.setAttribute(Const.CURRENTADMIN,user);
                return ServletResponse.getRequestSuccess("登录成功",user);
            }
        }
        return ServletResponse.getRequestFailed("用户名或密码错误");
    }
}
