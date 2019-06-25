package com.neuedu.service;

import com.neuedu.common.Const;
import com.neuedu.common.GuavaCache;
import com.neuedu.common.ServletResponse;
import com.neuedu.dao.UserMapper;
import com.neuedu.pojo.User;
import com.neuedu.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper um;

    //登录
    @Override
    public ServletResponse login(String name, String psw ,HttpSession session) {
        /*
        * 判断用户名和密码是否为null
        * 判断用户名是否存在，若不存在，提示信息“用户名不存在”
        * 根据用户名和密码查询用户
        * 若查询到的用户不为null   提示信息“登录成功”
        * 否则，提示“用户名或密码错误”；
        * */
        if (name == null || name.length() == 0)
        {
            return ServletResponse.getRequestFailed("用户名不能为空!");
        }
        if (psw == null || psw.length() == 0)
        {
            return ServletResponse.getRequestFailed("密码不能为空!");
        }
        int i = um.selectUserByUserName(name);
        if (i == 0)
        {
           return ServletResponse.getRequestFailed("用户名不存在，请重新输入");
        }
        //密码加密
        String md5Encode = MD5Util.MD5Encode(psw, null);
        User user = um.selectUserInfoByUserNameAndPsw(name, md5Encode);
        if (user == null)
        {
            return ServletResponse.getRequestFailed("用户名或密码错误");
        }
        session.setAttribute(Const.CURRENTUSER,user);
        return ServletResponse.getRequestSuccess("登录成功",user);
    }

    //注册
    @Override
    public ServletResponse register(User user) {
        /*
         * 非空校验
         * 判断用户名是否存在
         * 判断密码是否存在
         * 密码加密（md5）
         * 注册
         *
         */

        if (!StringUtils.isNotBlank(user.getUsername()))
        {
            return ServletResponse.getRequestFailed("用户名错误");
        }
        if (!StringUtils.isNotBlank(user.getPassword()))
        {
            return ServletResponse.getRequestFailed("密码错误");
        }
        if (!StringUtils.isNotBlank(user.getEmail()))
        {
            return ServletResponse.getRequestFailed("邮箱错误");
        }
        if (!StringUtils.isNotBlank(user.getPhone()))
        {
            return ServletResponse.getRequestFailed("手机号码错误");
        }
        if (!StringUtils.isNotBlank(user.getQuestion()))
        {
            return ServletResponse.getRequestFailed("密保问题错误");
        }
        if (!StringUtils.isNotBlank(user.getAnswer()))
        {
            return ServletResponse.getRequestFailed("密保答案错误");
        }
        // 检查用户名和邮箱是否有效
        ServletResponse servletResponse1 = checkValid(user.getUsername(), Const.USERNAME);
        if (servletResponse1.isReqFailed())
        {
            return ServletResponse.getRequestFailed("用户名存在");
        }

        ServletResponse servletResponse2 = checkValid(user.getEmail(), Const.EMAIL);
        if (servletResponse2.isReqFailed())
        {
            return ServletResponse.getRequestFailed("邮箱存在");
        }

       //密码加密
        String md5Encode = MD5Util.MD5Encode(user.getPassword(), null);
        user.setPassword(md5Encode);

        //设置用户角色
        user.setRole(Const.COMMONUSER);

        //调用添加的方法
        int insert = um.insert(user);
        if (insert == 0)
        {
            return ServletResponse.getRequestFailed("注册失败");
        }

        return ServletResponse.getRequestSuccess("注册成功");
    }

    //获取当前登录用户的详细信息
    @Override
    public ServletResponse getUserInfo(HttpSession session) {
        Object user = session.getAttribute("user");
        if (null == user)
        {
            return ServletResponse.getRequestFailed("用户未登录，无法获得当前用户信息");
        }
        return ServletResponse.getRequestSuccess(user);
    }

    //忘记密码
    //1.获取密保问题
    @Override
    public ServletResponse forgetPsw_Ques(String username) {
        if (!StringUtils.isNotBlank(username))
        {
            return ServletResponse.getRequestFailed("用户名为空");
        }
        ServletResponse servletResponse = checkValid(username, Const.USERNAME);
        //若成功，说明没有此用户名
        if (servletResponse.isReqSuccess())
        {
            return ServletResponse.getRequestFailed("没有此用户");
        }
        String question = um.selectQuestionByUserName(username);
        return ServletResponse.getRequestSuccess(question);
    }

    //2.提交问题答案
    /**
     * 判断传入参数是否为空
     * 查询答案是否与用户名及问题匹配
     * 若成功，
     * 创建token放到缓存中
     * 若失败，
     * 提示回答错误
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @Override
    public ServletResponse forgetPsw_Answer(String username, String question, String answer) {
        if (!StringUtils.isNotBlank(username))
        {
            return ServletResponse.getRequestFailed("用户名不能为空");
        }
        if (!StringUtils.isNotBlank(question))
        {
            return ServletResponse.getRequestFailed("问题不能为空");
        }
        if (!StringUtils.isNotBlank(answer))
        {
            return ServletResponse.getRequestFailed("答案不能为空");
        }
        int i = um.queryUserAnswerIsRight(username, question, answer);
        if (i>0)
        {
            String uuid = UUID.randomUUID().toString();
            GuavaCache.putCache(Const.TOKENCACHE,uuid);
            return ServletResponse.getRequestSuccess("验证成功",uuid);
        }
        return ServletResponse.getRequestFailed("回答错误");
    }

    //3.重置密码
    @Override
    public ServletResponse forgetPsw_ResetPsw(String username, String passwordNew, String forgetToken) {
        //判断是否为空
        if (!StringUtils.isNotBlank(username))
        {
            return ServletResponse.getRequestFailed("用户名不能为空");
        }
        if (!StringUtils.isNotBlank(passwordNew))
        {
            return ServletResponse.getRequestFailed("新密码不能为空");
        }
        if (!StringUtils.isNotBlank(forgetToken))
        {
            return ServletResponse.getRequestFailed("token不能为空");
        }
        //判断缓存里的token是否过期
        String cache = GuavaCache.getCache(Const.TOKENCACHE);
        if (StringUtils.isNotBlank(cache))
        {
            //比较用户传过来的token与缓存中的token是否一致
            //若一致，可以修改密码
            if (StringUtils.equals(forgetToken,cache))
            {
                //密码加密
                String md5Encode = MD5Util.MD5Encode(passwordNew, null);
                int i = um.updateUserPswByUser(username, md5Encode);
                if (i>0)
                {
                    return ServletResponse.getRequestSuccess("修改密码成功");
                }
            }
        }
        return ServletResponse.getRequestFailed("修改密码失败");
    }

    //登录状态中重置密码
    @Override
    public ServletResponse reset_password(HttpSession session, String passwordOld, String passwordNew) {
        //判断是否为登录状态
        User user = (User)session.getAttribute(Const.CURRENTUSER);
        if (null == user)
        {
            return ServletResponse.getRequestFailed("未登录，请登录后使用");
        }
        //判断非空
        if (!StringUtils.isNotBlank(passwordOld))
        {
            return ServletResponse.getRequestFailed("旧密码不能为空");
        }
        if (!StringUtils.isNotBlank(passwordNew))
        {
            return ServletResponse.getRequestFailed("新密码不能为空");
        }
        //验证旧密码是否正确
        //密码加密
        String md5Encode = MD5Util.MD5Encode(passwordOld, null);

        if (!StringUtils.equals(md5Encode,user.getPassword()))
        {
            return ServletResponse.getRequestFailed("旧密码输入错误");
        }
        //新旧密码不能相同
        if (StringUtils.equals(passwordNew,passwordOld))
        {
            return ServletResponse.getRequestFailed("新旧密码不能相同");
        }
        //更新密码
        //密码加密
        String encode = MD5Util.MD5Encode(passwordNew, null);
        int i = um.updateUserPswByUser(user.getUsername(), encode);
        if (i>0)
        {
            user.setPassword(encode);
            session.setAttribute(Const.CURRENTUSER,user);
            return ServletResponse.getRequestSuccess("成功修改密码");
        }
        return ServletResponse.getRequestFailed("修改失败");
    }

    //登录状态更新个人信息
    @Override
    public ServletResponse update_information(HttpSession session,User user) {
        //判断登录状态
        User loginUser = (User) session.getAttribute(Const.CURRENTUSER);
        if (null == loginUser)
        {
            return ServletResponse.getRequestFailed("未登录，请登录后使用");
        }
        user.setUsername(loginUser.getUsername());
        int i = um.updateByPrimaryKeySelective(user);
        if (i>0)
        {
            return ServletResponse.getRequestSuccess("修改成功");
        }
        return ServletResponse.getRequestFailed("修改失败");
    }

    //退出登录
    @Override
    public ServletResponse logout(HttpSession session) {
        session.removeAttribute(Const.CURRENTUSER);
        Object attribute = session.getAttribute(Const.CURRENTUSER);
        if (null != attribute)
        {
            return ServletResponse.getRequestFailed("服务端异常");
        }
        return ServletResponse.getRequestSuccess("退出成功");
    }


    //     检查用户名和邮箱是否有效
    public ServletResponse checkValid(String val, String type)
    {
        if (!StringUtils.isNotBlank(val))
        {
            return ServletResponse.getRequestFailed("请输入对应的值");
        }
        if (StringUtils.isNoneBlank(type))
        {
            if (Const.USERNAME.equals(type))
            {
                int i = um.selectUserByUserName(val);
                if (i >0)
                {
                    return ServletResponse.getRequestFailed("用户名已存在...");
                }
            }
            if (Const.EMAIL.equals(type))
            {
                int i = um.selectEmailIsExists(val);
                if (i>0)
                {
                    return ServletResponse.getRequestFailed("邮箱已存在...");
                }
            }
        }else
        {
            return ServletResponse.getRequestFailed("校验类型有误");
        }
        return ServletResponse.getRequestSuccess("校验通过，可以注册");
    }

}
