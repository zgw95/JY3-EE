package com.zgw.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * 商品工具类
 */
public class ProductAction {


//    public static void main(String[] args) {
//        System.out.println(getProIdByTimeMillis());
//    }

    //获得商品的id。(id = 当前毫秒值 + 一个随机数)
    public static String getProIdByRule()
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

    //获得商品的上架日期。(将String类型转成Date类型)
    public static Date getProStockByRule(String str)
    {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            java.util.Date parse = sdf.parse(str);
            long time = parse.getTime();
            date = new Date(time);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }




}
