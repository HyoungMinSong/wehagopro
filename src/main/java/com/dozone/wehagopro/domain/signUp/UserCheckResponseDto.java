package com.dozone.wehagopro.domain.signUp;

import lombok.Data;

@Data
public class UserCheckResponseDto {
    private String checkId;
    private String checkEmail;
    private String checkPhoneNumber;

    public UserCheckResponseDto(String checkId, String checkEmail, String checkPhoneNumber) {
        this.checkId = checkId;
        this.checkEmail = checkEmail;
        this.checkPhoneNumber = checkPhoneNumber;
    }

}
