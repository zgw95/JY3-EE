package com.neuedu.service;

import com.neuedu.common.ServletResponse;
import com.neuedu.pojo.Category;

import javax.servlet.http.HttpSession;

public interface Categoryservice {

    //获取品类子节点(平级)
    ServletResponse getCategory(Integer cId);

    //增加节点（添加新类别）
    ServletResponse addCategory(Category category);

}
