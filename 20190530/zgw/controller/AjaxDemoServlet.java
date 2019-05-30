package com.zgw.controller;

import com.google.gson.Gson;
import com.zgw.dao.UserDao;
import com.zgw.dao.UserDaoImpl;
import com.zgw.pojo.User;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AjaxDemoServlet",urlPatterns = "/server.do")
public class AjaxDemoServlet extends HttpServlet {
    private UserDao ud;

    @Override
    public void init() throws ServletException {
        ud = new UserDaoImpl();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
//        User user1 = new User("1", "张一", "111111");
//        User user2 = new User("2", "张二", "222222");
//        User user3 = new User("3", "张三", "333333");
//        List<User> users = new ArrayList<>();
//        users.add(user1);
//        users.add(user2);
//        users.add(user3);
        //
//        List<User> users = ud.queryALl();
//        JSONArray jsonArray = JSONArray.fromObject(users);
//        resp.getWriter().write(jsonArray.toString());


        List<User> users = ud.queryALl();
        Gson gson = new Gson();
        String toJson = gson.toJson(users);
        resp.getWriter().write(toJson);




    }
}
