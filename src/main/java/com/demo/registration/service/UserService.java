package com.demo.registration.service;

import com.demo.registration.dao.UserDao;
import com.demo.registration.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User submitLogin(String userName, String password)
    {

        Subject subject= SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
            token.setRememberMe(true);
            //登录
            subject.login(token);
        }catch (Exception e)
        {
            e.printStackTrace();

            return  new User();
            //认证没成功
        }
        User result = userDao.findUserByUsername(userName);
        return  result;
    }

    public User getUser() {
        String userName = getCurrentUser();
        return userDao.findUserByUsername(userName);
    }
    //获取当前用户
    public String getCurrentUser()
    {
        return (String)SecurityUtils.getSubject().getPrincipal();
    }
}
