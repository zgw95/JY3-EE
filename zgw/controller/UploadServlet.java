package com.zgw.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


@MultipartConfig        //上传文件需要写的注解
@WebServlet(name = "UploadServlet",urlPatterns = "/upload.do")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("myFile");
        String submittedFileName = part.getSubmittedFileName();
        InputStream is = part.getInputStream();
        OutputStream os = new FileOutputStream("E:\\webE_commerce_img\\" + submittedFileName);
        int buffer = 0;
        byte[] bs = new byte[1024];
        while ((buffer = is.read(bs)) != -1)
        {
            os.write(bs,0,buffer);
        }
        os.close();
        is.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
