package com.wty.JAVAEE.service;

import com.wty.JAVAEE.mapper.UserMapper;
import com.wty.entity.UserCar;
import com.wty.entity.UserRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
//更改手机号
    public void editPhone(String username, String phone) {
        userMapper.editPhone(username,phone);
    }

    public String getLeftCount(String username) {
       return userMapper.getLeftCount(username);
    }

    public List<UserCar> getUserCarList(String username) {
        return userMapper.getUserCarList(username);
    }

    public void delCar(String car_card) {
        userMapper.delCar(car_card);
    }

    public void addCar(String car_card,String username) {
        userMapper.addCar(car_card,username);
    }

//获取会员数据
    public List<UserRule> getRole() {
       return userMapper.getRole();
    }
//修改会员
    public void editMember(String username, String user_type_name) {
        userMapper.editMember(username,user_type_name);
    }

    public String getMark(String username) {
        return userMapper.getMark(username);
    }

    public void addCard(String card_time,String card_endtimeStr, String username) {
        userMapper.addCard(card_time,card_endtimeStr,username);
    }

    public void delMark(String delMark, String username) {
        userMapper.delMark(Integer.parseInt(delMark) ,username);
    }

    public String getIdByname(String user_type_name) {
       return userMapper.getIdByname(user_type_name);
    }
}
