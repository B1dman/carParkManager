package com.wty.entity;

import java.util.Date;

public class UserCard {
    private String username;
    private String card_name;
    private String index;
    private String card_time;
    private Date card_endtime;

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getCard_time() {
        return card_time;
    }

    public void setCard_time(String card_time) {
        this.card_time = card_time;
    }

    public Date getCard_endtime() {
        return card_endtime;
    }

    public void setCard_endtime(Date card_endtime) {
        this.card_endtime = card_endtime;
    }
}
