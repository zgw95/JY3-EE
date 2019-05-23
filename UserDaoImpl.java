package com.zgw.dao;

import com.zgw.dataSource.MyDruidDataSource;
import com.zgw.pojo.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao{

    private QueryRunner qr = null;

    private MyDruidDataSource mdds = MyDruidDataSource.getMyDruidDataSource();

    public UserDaoImpl()
    {
        //构造器里初始化QueryRunner对象
        qr = new QueryRunner();
    }

    @Override
    public List<User> getAllUser() {
        String sql = "select * from user";
        List<User> users = null;
        Connection conn = mdds.getConnection();
        try {
            users = qr.query(conn, sql, new BeanListHandler<>(User.class));
            if (null != users)
            {
                System.out.println("查询成功");
            }else {
                System.out.println("查询失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtils.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void deleteUser(String num) {
        String sql = "DELETE FROM user WHERE id = ?";
        Connection conn = mdds.getConnection();
        try {
            int rs = qr.update(conn, sql, num);
            if (rs >= 1)
            {
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
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

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE user set username = ? , password = ? WHERE id = ?";
        Connection conn = mdds.getConnection();
        try {
            int rs = qr.update(conn, sql, user.getUsername(), user.getPassword(), user.getId());
            if (rs >= 1)
            {
                System.out.println("修改成功");
            }else {
                System.out.println("修改失败");
            }
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


}
