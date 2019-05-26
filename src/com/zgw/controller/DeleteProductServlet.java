package com.zgw.controller;

import com.zgw.dao.ProductDao;
import com.zgw.dao.ProductDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteProductServlet",urlPatterns = "/deleteProduct.do")
public class DeleteProductServlet extends HttpServlet {

    private static ProductDao pd;

    @Override
    public void init() throws ServletException {
        pd = new ProductDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pro_id = request.getParameter("pro_id");
        System.out.println("获取的id："+pro_id);
        pd.deleteProduct(pro_id);
        request.getRequestDispatcher("queryProduct.do").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
