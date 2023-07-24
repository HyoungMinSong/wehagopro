package com.dozone.wehagopro.domain.signUp;

import lombok.Data;

@Data
public class UserSignUpDto {

    private int no;
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String email;

    public UserSignUpDto(String id, String password, String name, String phoneNumber, String email) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
