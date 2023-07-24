package com.dozone.wehagopro.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRequestDto {
    private String userid;
    private String password;
}
