package com.zgw.dao;

import com.zgw.dataSourse.MyDruidDataSource;
import com.zgw.pojo.Product;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductDaoImpl implements ProductDao{

    private MyDruidDataSource mdds;

    private QueryRunner qr;

    public ProductDaoImpl()
    {
        this.mdds = MyDruidDataSource.getMyDruidDataSource();
        this.qr = new QueryRunner();
    }

    @Override
    public void addOneProduct(Product product) {
        Connection conn = mdds.getConnection();
        String sql = "insert into product values (?,?,?,?,?,?,?,?,?)";
        try {
            qr.update(conn,sql,product.getPro_id(),product.getPro_name(),product.getPro_price(),product.getPro_img(),product.getPro_des(),product.getPro_stock(),product.getPro_date(),product.getPro_cate_id(),product.getPro_factory());

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
