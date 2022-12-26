package com.wty.JAVAEE.service;

import com.wty.JAVAEE.mapper.PayMapper;
import com.wty.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayService {
    @Autowired
    PayMapper payMapper;


    public List<UserPaycar> getPaycar(String username) {
        return payMapper.getPaycar(username);
    }

    public UserInfo getcarUser(String car_card) {
        return payMapper.getcarUser(car_card);
    }

    public UserRule getRule(String user_type_id) {
        return payMapper.getRule(user_type_id);
    }

    public void setTime(String nowdate,String car_card) {
        payMapper.setTime(nowdate,car_card);
    }

    public void setFreetime(String nowdate1,String car_card) {
        payMapper.setFreetime(nowdate1,car_card);
    }

    public void setIndex(String index) {
        payMapper.setIndext(index);
    }

    public void addPay(String username,String pay_money, String car_card,String car_intime,String car_paytime) {
        payMapper.addPay(username,pay_money,car_card,car_intime,car_paytime);
    }

    public List<UserPaycar> getAllPaycar(String username,String currentPage,int pageSize) {
        int pos = (Integer.parseInt(currentPage)-1)*pageSize;
        return payMapper.getAllPaycar(username,pos,pageSize);
    }

    public OtherCar getOtherCar(String car_card) {
        return payMapper.getOtherCar(car_card);
    }

    public void return0(String car_card) {
        payMapper.return0(car_card);
    }

    public List<UserPaycar> getAllPaycar1(String username) {
        return payMapper.getAllPaycar1(username);
    }
}
