package com.neuedu.controller.back;

import com.neuedu.common.Const;
import com.neuedu.common.ServletResponse;
import com.neuedu.pojo.User;
import com.neuedu.service.Categoryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/manage/category")
@RestController
public class CateCon {

    @Autowired
    private Categoryservice cs;


    //获取品类子节点(平级)
    @RequestMapping("/get_category.do")
    public ServletResponse get_category(HttpSession session, @RequestParam(defaultValue = "0") Integer cId)
    {
        User user = (User)session.getAttribute(Const.CURRENTADMIN);
        if (null == user)
        {
            return ServletResponse.getRequestFailed("请登录");
        }
        if (user.getRole().intValue() != Const.COMMONADMIN)
        {
            return ServletResponse.getRequestFailed("您没有此权限");
        }
        return cs.getCategory(cId);
    }

    //增加节点（添加新类别）
    @RequestMapping("/add_category.do")
    public ServletResponse add_category(HttpSession session,@RequestParam(defaultValue = "0") Integer parentId,String name)
    {
        User user = (User)session.getAttribute(Const.CURRENTADMIN);
        if (null == user)
        {
            return ServletResponse.getRequestFailed("请登录");
        }
        if (user.getRole().intValue() != Const.COMMONADMIN)
        {
            return ServletResponse.getRequestFailed("您没有此权限");
        }


        return null;
    }
}
