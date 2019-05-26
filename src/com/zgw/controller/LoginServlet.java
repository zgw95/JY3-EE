package com.zgw.controller;

import com.zgw.dao.UserDao;
import com.zgw.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

    private static UserDao ud;

    public LoginServlet() {
        ud = new UserDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        boolean result = ud.login(name, password);

        request.setAttribute("userName",name);
        request.setAttribute("information","用户名密码错误，请重新输入");

        if (result == true){
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else {
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
