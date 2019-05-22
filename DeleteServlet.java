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

@WebServlet(name = "DeleteServlet",urlPatterns = "/deleteUser.do")
public class DeleteServlet extends HttpServlet {

    private static UserDao ud;

    @Override
    public void init() throws ServletException {
        ud = new UserDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id=request.getParameter("id");
        System.out.println("Servlet结果："+ id);
        if (null != id)
        {
            ud.deleteUser(id);
            List<User> allUser = ud.getAllUser();
            request.setAttribute("users",allUser);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
