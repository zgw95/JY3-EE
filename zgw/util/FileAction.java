package com.zgw.util;

import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 文件工具类
 */
public class FileAction {

    //上传图片
    public static String uploadFile(Part part)
    {
        String submittedFileName = part.getSubmittedFileName();
        InputStream is = null;
        OutputStream os = null;
        //创建UUID通用唯一标识符
        UUID uuid = UUID.randomUUID();
        String name = uuid + submittedFileName;
        try {
            is = part.getInputStream();
            os = new FileOutputStream("E:\\webE_commerce_img\\" + name);
            int buffer = 0;
            byte[] bs = new byte[1024];
            while ((buffer = is.read(bs)) != -1)
            {
                os.write(bs,0,buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    return name;
    }
}
