package com.wty.entity;

import java.util.Date;

public class UserCar {
    private String car_card;
    private Date car_intime;
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCar_card() {
        return car_card;
    }

    public void setCar_card(String car_card) {
        this.car_card = car_card;
    }

    public Date getCar_intime() {
        return car_intime;
    }

    public void setCar_intime(Date car_intime) {
        this.car_intime = car_intime;
    }
}
