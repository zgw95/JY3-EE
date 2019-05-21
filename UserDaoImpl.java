package com.zgw.dao;

import com.zgw.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

    Connection conn = DBUtil.getConnection();

    @Override
    public void register(String userName, String passWord) {

        try {
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

    @Override
    public boolean updatePassword(String userName, String oldPassword, String newPassword) {
        String selectUsername = "select username from user";
        String selectPassword = "select password from user where username = ? ";
        String setNewPassword = "update user set password = ? where username = ? ";

        PreparedStatement pre = null;
        PreparedStatement pre2 = null;
        PreparedStatement pre3 = null;
        try {
            pre = conn.prepareStatement(selectUsername);
            ResultSet rs = pre.executeQuery();
            List<String> usernameList = new ArrayList<String>();
            while (rs.next()) {
                usernameList.add(rs.getString(1));
            }
            //判断集合中是否存在所要登录的username
            if (usernameList.contains(userName)) {
                System.out.println("用户名存在");
                //查找username对应的password
                try{
                    pre2 = conn.prepareStatement(selectPassword);
                    pre2.setString(1,userName);
                    ResultSet rs2 = pre2.executeQuery();
                    rs2.next();
                    String psw = rs2.getString("password");
                    //判断数据库与登录页面提交的password是否一致
                    if (psw.equals(oldPassword)) {
                        System.out.println("原密码正确");
                        try{
                            pre3 = conn.prepareStatement(setNewPassword);
                            pre3.setString(1,newPassword);
                            pre3.setString(2,userName);

                            System.out.println(pre3);

                            pre3.executeUpdate();
                            System.out.println("修改成功");
                            return true;
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("原密码错误");
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("该用户名不存在");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ResultSet selectAllUser() {
        String selectUsername = "select * from user";
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(selectUsername);
            ResultSet rs = pre.executeQuery();
//            List<String> usernameList = new ArrayList<String>();
//            while (rs.next()) {
//                usernameList.add(rs.getString(1));
//            }
//            return usernameList;
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




}