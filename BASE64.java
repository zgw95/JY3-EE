package com.zgw.test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BASE64 {

    /**
     * BASE64解密
     *
     */
    public static String decryptBASE64(String key) throws Exception {
        byte[] key2 = new BASE64Decoder().decodeBuffer(key);
        String result= new String (key2);
        return (result);
    }

    /**
     * BASE64加密
     *
     */
    public static String encryptBASE64(String key) throws Exception {
        byte[] key2 = key.getBytes();
        String result = new BASE64Encoder().encodeBuffer(key2);
        return (result);
    }





}
