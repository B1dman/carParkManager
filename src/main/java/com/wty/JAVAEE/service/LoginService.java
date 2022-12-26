package com.wty.JAVAEE.service;

import com.wty.JAVAEE.mapper.LoginMapper;
import com.wty.entity.User;
import com.wty.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    LoginMapper loginMapper;


//    登录
    public User login(String username, String password) {
        User user = null;
        user = loginMapper.login(username,password);
        return user;
    }
//获取用户信息
    public UserInfo getuser(String username){
        return loginMapper.getuser(username);
    }


}
