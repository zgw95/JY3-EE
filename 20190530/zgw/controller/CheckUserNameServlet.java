package com.zgw.controller;

import com.zgw.dao.UserDao;
import com.zgw.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CheckUserNameServlet",urlPatterns = "/checkUserName.do")
public class CheckUserNameServlet extends HttpServlet {

    private UserDao ud;

    @Override
    public void init() throws ServletException {
        ud = new UserDaoImpl();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        boolean result = ud.checkUserName(name);

        System.out.println("用户名为："+name);
        System.out.println("结果为："+ result);
        //如果查到此用户，返回true， 赋值ok。
        if (result){
            resp.getWriter().write("ok");
        }

    }
}
