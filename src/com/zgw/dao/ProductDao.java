package com.zgw.dao;

import com.zgw.pojo.Product;

import java.util.List;

public interface ProductDao {

    //添加商品
    void addOneProduct(Product product);

    //查询商品
    List<Product> queryProduct();

    //修改产品
    void updateProduct(Product product);

    //删除商品
    void deleteProduct(String id);

}
