package com.dozone.wehagopro.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}