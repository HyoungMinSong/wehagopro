package com.dozone.wehagopro.domain;


import lombok.Data;
import sun.util.resources.ga.LocaleNames_ga;

@Data
public class Login {

    private String t_user_id;
    private String t_user_password;
    private String t_user_name;
    private String t_user_email;
    private String t_user_phone;
    private String t_user_new_password;
    private String t_user_new_password_check;



    public Login() {
        // 기본 생성자 추가
    }

//    public Login(String t_user_name, String t_user_email, String t_user_phone) {
//        this.t_user_name = t_user_name;
//        this.t_user_email = t_user_email;
//        this.t_user_phone = t_user_phone;
//    }
}