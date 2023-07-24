package com.dozone.wehagopro.domain;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserDto {
    private int t_user_no;
    private String t_user_id;
    private String t_user_password;
    private String t_user_name;
    private String t_user_call;
    private String t_user_phone;
    private String t_user_email;
    private String t_user_photo_name;
    private String t_user_photo_path;
    private Date t_user_sign_date;
    private Date t_user_delete_date;
    private int t_user_state;
    private Date t_user_update_date;

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.t_user_password = passwordEncoder.encode(t_user_password);
    }
}