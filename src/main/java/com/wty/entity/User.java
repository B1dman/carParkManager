package com.wty.entity;

public class User {
    private String username;
    private String password;
    private String user_type_id;
    private String user_type_name;


    public String getUser_type_id() {
        return user_type_id;
    }

    public void setUser_type_id(String user_type_id) {
        this.user_type_id = user_type_id;
    }

    public String getUser_type_name() {
        return user_type_name;
    }

    public void setUser_type_name(String user_type_name) {
        this.user_type_name = user_type_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
