package com.neuedu.service;

import com.neuedu.common.ServletResponse;
import com.neuedu.dao.CategoryMapper;
import com.neuedu.pojo.Category;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CategoryServiceImpl implements Categoryservice{

    @Autowired
    private CategoryMapper cm;

    //获取品类子节点(平级)
    @Override
    public ServletResponse getCategory(Integer cId) {
        List<Category> categoryList = cm.querySubCategory(cId);
        if (!categoryList.isEmpty())
        {
            return ServletResponse.getRequestSuccess("查询成功",categoryList);
        }
        return ServletResponse.getRequestFailed("查询失败");
    }

    @Override
    public ServletResponse addCategory(Category category) {
        if (!StringUtils.isNotBlank(category.getName()))
        {
            return ServletResponse.getRequestFailed("类别名不能为空");
        }
        cm.insertSelective(category);


        return null;
    }

}
