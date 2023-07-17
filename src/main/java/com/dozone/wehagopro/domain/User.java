package com.dozone.wehagopro.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class User {

    private Integer t_user_no;
    private String t_user_id;
    private String t_user_password;
    private String t_user_name;
    private String t_user_phone;
    private String t_user_email;
    private String t_user_photo_name;
    private String t_user_photo_path;
    private Date t_user_sign_date;
    private Date t_user_delete_date;
    private Integer t_user_state;
    private Date t_user_update_date;

    public User() {
    }

    public User(String t_user_id, String t_user_password, String t_user_name, String t_user_phone, String t_user_email, String t_user_photo_name, String t_user_photo_path, Date t_user_sign_date, Date t_user_delete_date, Integer t_user_state, Date t_user_update_date) {
        this.t_user_id = t_user_id;
        this.t_user_password = t_user_password;
        this.t_user_name = t_user_name;
        this.t_user_phone = t_user_phone;
        this.t_user_email = t_user_email;
        this.t_user_photo_name = t_user_photo_name;
        this.t_user_photo_path = t_user_photo_path;
        this.t_user_sign_date = t_user_sign_date;
        this.t_user_delete_date = t_user_delete_date;
        this.t_user_state = t_user_state;
        this.t_user_update_date = t_user_update_date;
    }

    public User(Integer t_user_no, String t_user_id, String t_user_password, String t_user_name, String t_user_phone, String t_user_email, String t_user_photo_name, String t_user_photo_path, Date t_user_sign_date, Date t_user_delete_date, Integer t_user_state, Date t_user_update_date) {
        this.t_user_no = t_user_no;
        this.t_user_id = t_user_id;
        this.t_user_password = t_user_password;
        this.t_user_name = t_user_name;
        this.t_user_phone = t_user_phone;
        this.t_user_email = t_user_email;
        this.t_user_photo_name = t_user_photo_name;
        this.t_user_photo_path = t_user_photo_path;
        this.t_user_sign_date = t_user_sign_date;
        this.t_user_delete_date = t_user_delete_date;
        this.t_user_state = t_user_state;
        this.t_user_update_date = t_user_update_date;
    }
}
