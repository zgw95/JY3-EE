package com.zgw.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static Properties pro;
    static
    {
        pro = new Properties();
        InputStream ins = DBUtil.class.getResourceAsStream("../settings.properties");
        try {
            pro.load(ins);
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
    //拿到配置文件中的数据
    private static final String URL = pro.getProperty("url");
    private static final String USER = pro.getProperty("user");
    private static final String PASSWORD = pro.getProperty("password");

    //获取连接
    public static Connection getConnection()
    {
        Connection conn = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;

    }
}

