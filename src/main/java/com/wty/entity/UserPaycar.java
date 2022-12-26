package com.wty.entity;

import java.util.Date;

public class UserPaycar {
    private String username;
    private String car_card;
    private String car_intime;
    private Date car_outtime;
    private Date car_paytime;
    private String pay_money;
    //状态
    private String status;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCar_card() {
        return car_card;
    }

    public void setCar_card(String car_card) {
        this.car_card = car_card;
    }

    public String getCar_intime() {
        return car_intime;
    }

    public void setCar_intime(String car_intime) {
        this.car_intime = car_intime;
    }

    public Date getCar_outtime() {
        return car_outtime;
    }

    public void setCar_outtime(Date car_outtime) {
        this.car_outtime = car_outtime;
    }

    public Date getCar_paytime() {
        return car_paytime;
    }

    public void setCar_paytime(Date car_paytime) {
        this.car_paytime = car_paytime;
    }

    public String getPay_money() {
        return pay_money;
    }

    public void setPay_money(String pay_money) {
        this.pay_money = pay_money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
