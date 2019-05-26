package com.zgw.dao;

import com.zgw.dataSourse.MyDruidDataSource;
import com.zgw.pojo.Product;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao{

    private MyDruidDataSource mdds;

    private QueryRunner qr;

    public ProductDaoImpl()
    {
        this.mdds = MyDruidDataSource.getMyDruidDataSource();
        this.qr = new QueryRunner();
    }

    //增
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
    //查
    @Override
    public List<Product> queryProduct() {
        Connection conn = mdds.getConnection();
        String sql = "select * from product";
        List<Product> products = null;
        BeanListHandler<Product> beanListHandler = new BeanListHandler<>(Product.class);
        try {
            products = qr.query(conn, sql, beanListHandler);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtils.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return products;
    }
    //改
    @Override
    public void updateProduct(Product product) {
        Connection conn = mdds.getConnection();
        String sql = "UPDATE product SET pro_name=? ,pro_price=? ,pro_img=? ,pro_des=? ,pro_stock=? ,pro_date=? ,pro_cate_id=? ,pro_factory=?  WHERE pro_id= ?";
        try {
            qr.update(conn,sql,product.getPro_name(),product.getPro_price(),product.getPro_img(),product.getPro_des(),product.getPro_stock(),product.getPro_date(),product.getPro_cate_id(),product.getPro_factory(),product.getPro_id());

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
    //删
    @Override
    public void deleteProduct(String id) {
        Connection conn = mdds.getConnection();
        String sql = "delete from product WHERE pro_id= ? ";
        try {
            qr.update(conn,sql,id);
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
