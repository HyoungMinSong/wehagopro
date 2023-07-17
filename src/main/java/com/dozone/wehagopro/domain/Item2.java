package com.dozone.wehagopro.domain;

import lombok.Data;
@Data
public class Item2 {

    private String t_user_id;
    private String t_user_password;

    public Item2() {

    }


    public Item2(String userid, String password) {

        this.t_user_id = userid;
        this.t_user_password = password;
    }

    public String getT_user_id() {
        return t_user_id;
    }

    public void setT_user_id(String t_user_id) {
        this.t_user_id = t_user_id;
    }

    public String getT_user_password() {
        return t_user_password;
    }

    public void setT_user_password(String t_user_password) {
        this.t_user_password = t_user_password;
    }
}