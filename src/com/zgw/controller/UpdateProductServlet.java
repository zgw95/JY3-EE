package com.zgw.controller;

import com.zgw.dao.ProductDao;
import com.zgw.dao.ProductDaoImpl;
import com.zgw.pojo.Product;
import com.zgw.util.FileAction;
import com.zgw.util.ProductAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "UpdateProductServlet",urlPatterns = "/updateProduct.do")
@MultipartConfig        //上传文件需要写的注解
public class UpdateProductServlet extends HttpServlet {

    private ProductDao pd;

    @Override
    public void init() throws ServletException {
        pd = new ProductDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String pro_name = request.getParameter("pro_name");
        String pro_price = request.getParameter("pro_price");
        //拿到图片并上传到服务器,返回图片的文件名
        Part pro_img = request.getPart("pro_img");
        String realPro_img = FileAction.uploadFile(pro_img);
        String pro_des = request.getParameter("pro_des");
        String pro_stock = request.getParameter("pro_stock");
        String pro_date = request.getParameter("pro_date");
        //调用方法将str ==》 date
        Date realPro_date = ProductAction.getProStockByRule(pro_date);
        String pro_cate_id = request.getParameter("pro_cate_id");
        String pro_factory = request.getParameter("pro_factory");

        Product product = new Product(id,pro_name,Double.valueOf(pro_price),realPro_img,pro_des,Short.valueOf(pro_stock),realPro_date,Short.valueOf(pro_cate_id),pro_factory);
        pd.updateProduct(product);
        request.getRequestDispatcher("queryProduct.do").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
