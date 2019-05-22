package com.zgw.controller;

import com.zgw.dao.UserDao;
import com.zgw.dao.UserDaoImpl;
import com.zgw.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet",urlPatterns = "/user.do")
public class UserServlet extends HttpServlet {

    private UserDao ud;

    @Override
    public void init() throws ServletException {
        ud =new UserDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> allUser = ud.getAllUser();
        request.setAttribute("users",allUser);
        request.getRequestDispatcher("index.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
