package com.zgw.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * 用户工具类
 */
public class UserAction {

    //生成用户id（当前毫秒值 + 一个随机数）
    public static String getUserIdByRule()
    {
        //获得当前毫秒值作为产品的id
        long l = System.currentTimeMillis();
        String timeMillis = String.valueOf(l);
        //获取100以内的随机自然数[0.100)
        Random random = new Random();
        int randomNum = random.nextInt(100);
        if (randomNum < 10)
        {
            return timeMillis + "0" + randomNum;
        }
        return timeMillis + randomNum;
    }

    //获得用户注册时的日期（java.sql.Date类型）
    public static java.sql.Date getUerDate()
    {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(date);
        java.sql.Date result = null;
        try {
            java.util.Date parse = sdf.parse(str);
            long time = parse.getTime();
            result = new java.sql.Date(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

}
