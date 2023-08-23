package com.dozone.wehagopro.domain.signUp;

import lombok.Data;

@Data
public class SignUpInviteUpdateDto {

    private int empNo;
    private String userId;
    private String userPw;

    private String userName;
    private String userEmail;
    private String userPhone;

    private int userNo; //나중에 추가하는 필드.
}
