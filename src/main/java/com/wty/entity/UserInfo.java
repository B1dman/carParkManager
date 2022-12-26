package com.wty.entity;

import java.util.Date;

public class UserInfo {
    private String username;
    private String name;
    private String user_type_name;
    private String car_count;
    private String phone;
    private Date user_freetime;


    public Date getUser_freetime() {
        return user_freetime;
    }

    public void setUser_freetime(Date user_freetime) {
        this.user_freetime = user_freetime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_type_name() {
        return user_type_name;
    }

    public void setUser_type_name(String user_type_name) {
        this.user_type_name = user_type_name;
    }

    public String getCar_count() {
        return car_count;
    }

    public void setCar_count(String car_count) {
        this.car_count = car_count;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
