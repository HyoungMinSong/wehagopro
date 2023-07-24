package com.dozone.wehagopro.domain;


import lombok.Data;
import sun.util.resources.ga.LocaleNames_ga;


public class Login {

    private String t_user_id;
    private String t_user_password;
    private String t_user_name;
    private String t_user_email;
    private String t_user_phone;



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

    public String getT_user_name() {
        return t_user_name;
    }

    public void setT_user_name(String t_user_name) {
        this.t_user_name = t_user_name;
    }

    public String getT_user_email() {
        return t_user_email;
    }

    public void setT_user_email(String t_user_email) {
        this.t_user_email = t_user_email;
    }

    public String getT_user_phone() {
        return t_user_phone;
    }

    public void setT_user_phone(String t_user_phone) {
        this.t_user_phone = t_user_phone;
    }

    public Login() {
        // 기본 생성자 추가
    }

//    public Login(String t_user_name, String t_user_email, String t_user_phone) {
//        this.t_user_name = t_user_name;
//        this.t_user_email = t_user_email;
//        this.t_user_phone = t_user_phone;
//    }
}