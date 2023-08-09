package com.dozone.wehagopro.domain.signUp;

import lombok.Data;

@Data
public class UserCheckResponseDto {
    private String checkId;
    private String checkEmail;

    public UserCheckResponseDto(String checkId, String checkEmail) {
        this.checkId = checkId;
        this.checkEmail = checkEmail;
    }
}
