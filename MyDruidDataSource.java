package com.zgw.dataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * 配置连接池
 */
public class MyDruidDataSource {

    private static Properties prop = new Properties();

    private static MyDruidDataSource mdds = null;

    private MyDruidDataSource()
    {

    }

    public static MyDruidDataSource getMyDruidDataSource() {

        if (null == mdds)
        {
            mdds = new MyDruidDataSource();
        }
        return mdds;
    }

    public Connection getConnection()
    {
        try {
            prop.load(MyDruidDataSource.class.getResourceAsStream("/mysql.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
                    Connection conn = dataSource.getConnection();
            return conn;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        MyDruidDataSource mdd = MyDruidDataSource.getMyDruidDataSource();
//        Connection connection = mdd.getConnection();
//        System.out.println(connection);
//    }




}
