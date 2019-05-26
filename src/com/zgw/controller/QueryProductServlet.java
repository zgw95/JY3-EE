package com.zgw.controller;

import com.zgw.dao.ProductDao;
import com.zgw.dao.ProductDaoImpl;
import com.zgw.pojo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QueryProductServlet",urlPatterns = "/queryProduct.do")
public class QueryProductServlet extends HttpServlet {

    private static ProductDao pd;

    @Override
    public void init() throws ServletException {
        pd = new ProductDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Product> products = pd.queryProduct();
        request.setAttribute("products",products);
        request.getRequestDispatcher("queryProduct.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
