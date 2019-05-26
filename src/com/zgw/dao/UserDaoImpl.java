package com.zgw.dao;

import com.zgw.dataSourse.MyDruidDataSource;
import com.zgw.pojo.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

    private MyDruidDataSource mdds;

    private QueryRunner qr;

    public UserDaoImpl()
    {
        this.mdds = MyDruidDataSource.getMyDruidDataSource();
        this.qr = new QueryRunner();
    }

    //注册
    @Override
    public void register(User user) {
        Connection conn = mdds.getConnection();
        String sql = "insert into user values (?,?,?,?)";
        try {
            qr.update(conn,sql,user.getId(),user.getName(),user.getPassword(),user.getDate());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtils.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    //登录
    @Override
    public boolean login(String userName, String passWord) {
        Connection conn = mdds.getConnection();
        String selectName = "select name from user";
        String selectPassword = "select password from user where name = ? ";
        ColumnListHandler columnListHandler = new ColumnListHandler();
        try {
            List<String> nameList = (List<String>) qr.query(conn, selectName, columnListHandler);
            System.out.println("用户名列表："+ nameList);

            //判断集合中是否存在所要登录的name
            if (nameList.contains(userName)) {
                System.out.println("用户名存在");
                //查找name对应的password
                ScalarHandler scalarHandler = new ScalarHandler();
                ColumnListHandler columnListHandler2 = new ColumnListHandler();
                String psw = (String) qr.query(conn, selectPassword, scalarHandler, userName);
                System.out.println("查到的密码："+psw);
                if (psw.equals(passWord)) {
                    System.out.println("欢迎登录。");
                    return true;
                } else {
                    System.out.println("密码错误，请重新尝试。");
                    return false;
                }
            }else {
                System.out.println("用户名不存在，请重试...");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
