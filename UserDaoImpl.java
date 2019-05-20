package com.zgw.dao;

import javafx.scene.transform.Scale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDaoImpl implements UserDao{

    private static final String URL = "jdbc:mysql://localhost:3306/ideatest";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "zgw19951004";

//    Connection conn = DBUtil.getConnection();

    @Override
    public void register(String userName, String passWord) {

        try {
           //将mysql驱动服务注册到当前程序下
           Class.forName("com.mysql.jdbc.Driver");
           Connection conn =  DriverManager.getConnection(URL,USERNAME,PASSWORD);
           String sql = "insert into user values(?,?)";
           PreparedStatement pre = conn.prepareStatement(sql);
           pre.setString(1,userName);
           pre.setString(2,passWord);
           pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
    @Override
    public boolean login(String userName,String passWord) {
        try {
            //将mysql驱动服务注册到当前程序下
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn =  DriverManager.getConnection(URL,USERNAME,PASSWORD);
            //遍历ser表，将数据库中所有username存入集合中
            String selectUsername = "select username from user";
            PreparedStatement pre = conn.prepareStatement(selectUsername);
            ResultSet rs = pre.executeQuery();
            List<String> usernameList = new ArrayList<String>();
            while (rs.next()) {
                usernameList.add(rs.getString(1));
            }
            //判断集合中是否存在所要登录的username
            if (usernameList.contains(userName)) {
                //查找username对应的password
                String selectPassword = "select password from user where username = ? ";
                PreparedStatement pre2 = conn.prepareStatement(selectPassword);
                pre2.setString(1,userName);
                ResultSet rs2 = pre2.executeQuery();
                rs2.next();
                String psw = rs2.getString("password");
                //判断数据库与登录页面提交的password是否一致
                if (psw.equals(passWord)) {
                    System.out.println("欢迎登录。");
                    return true;
                } else {
                    System.out.println("密码错误，请重新尝试。");
                    return false;
                }

            } else {
                System.out.println("用户名不存在，请重试...");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return false;
    }



}