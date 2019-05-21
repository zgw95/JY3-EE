package com.zgw.test;

import com.zgw.dao.UserDaoImpl;
import com.zgw.test.BASE64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Scanner;

/**
 * 测试类
 */
public class Test {


    public static void main(String[] args) throws UnsupportedEncodingException {
        //测试登录
//        UserDaoImpl a = new UserDaoImpl();
//        Scanner scanner= new Scanner(System.in);
//        System.out.print("请输入用户名：");
//        String userName = scanner.next();
//        System.out.print("请输入密码：");
//        String passWord = scanner.next();
//        Boolean rs = a.login(userName,passWord);
//        System.out.println("登录结果为:" +rs);

        //测试base加密
        Scanner scanner= new Scanner(System.in);
        System.out.print("请输入用户名：");
        String uName = scanner.next();
        System.out.print("请输入密码：");
        String pWord = scanner.next();

        try {
            //加密
            String uName2 = BASE64.encryptBASE64(uName);
            String pWord2 = BASE64.encryptBASE64(pWord);
            System.out.print("加密后用户名："+uName2);
            System.out.print("加密后密码："+pWord2);
            //解密
            String uName3 = BASE64.decryptBASE64(uName2);
            String pWord3 = BASE64.decryptBASE64(pWord2);
            System.out.println("解密后用户名："+uName3);
            System.out.println("解密后密码："+pWord3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //编码
        final byte[] textByte = uName.getBytes("UTF-8");
        final String encodedText = Base64.getEncoder().encodeToString(textByte);
        System.out.println("加密后："+encodedText);
        //解码
        final byte[] textByte2 = Base64.getDecoder().decode(encodedText);
        final String decodeText= new String(textByte2);
        System.out.println("解密后:"+decodeText);



    }

}
