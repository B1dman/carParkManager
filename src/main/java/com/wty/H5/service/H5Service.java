package com.wty.H5.service;

import com.wty.H5.mapper.H5Mapper;
import com.wty.entity.H5entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class H5Service {
    @Autowired
    H5Mapper h5Mapper;

    public  List<Car> getCar() {
        return h5Mapper.getCar();
    }

    public TipRule getTicket(String id) {
        return h5Mapper.getTicket(id);
    }

    public HomeUser getHome(String date) {
        return h5Mapper.getHome(date);
    }

    public void pay(String car_card) {
        h5Mapper.pay(car_card);
    }

    public List<Card> getCard() {
        return h5Mapper.getCard();
    }

    public List<Cars> getCarlist() {
        return h5Mapper.getCarlist();
    }

    public void delCar(String car_card) {
        h5Mapper.delCar(car_card);
    }

    public String getMoney(String id) {
        return h5Mapper.getMoney(id);
    }

    public TipRule getRule(String money) {
        return h5Mapper.getRule(money);
    }

    public void getFalse(String id) {
        h5Mapper.getFalse(id);
    }
}
