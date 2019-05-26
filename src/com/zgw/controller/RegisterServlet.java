package com.zgw.controller;

import com.zgw.dao.UserDao;
import com.zgw.dao.UserDaoImpl;
import com.zgw.pojo.User;
import com.zgw.util.UserAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "RegisterServlet",urlPatterns = "/register.do")
public class RegisterServlet extends HttpServlet {

    private static UserDao ud;

    public RegisterServlet() {
        ud = new UserDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用方法，生成用户id
        String id = UserAction.getUserIdByRule();
        //调用方法，生成用户注册日期
        java.sql.Date date = UserAction.getUerDate();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        System.out.println("用户id"+id);
        System.out.println("注册日期"+date);
        System.out.println("用户名"+name);
        System.out.println("密码"+password);
        User user = new User(id,name,password,date);
        ud.register(user);
        request.setAttribute("successInf","注册成功，请登录");
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
