package com.wty.entity;

public class UserRule {
    private String user_type_id;
    private String hour_money;
    private String free_hour;
    private String max_car_count;


    public String getUser_type_id() {
        return user_type_id;
    }

    public void setUser_type_id(String user_type_id) {
        this.user_type_id = user_type_id;
    }

    public String getHour_money() {
        return hour_money;
    }

    public void setHour_money(String hour_money) {
        this.hour_money = hour_money;
    }

    public String getFree_hour() {
        return free_hour;
    }

    public void setFree_hour(String free_hour) {
        this.free_hour = free_hour;
    }

    public String getMax_car_count() {
        return max_car_count;
    }

    public void setMax_car_count(String max_car_count) {
        this.max_car_count = max_car_count;
    }
}
