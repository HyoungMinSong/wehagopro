package com.dozone.wehagopro.service;

import com.dozone.wehagopro.domain.MemberSignUpRequestDto;
import com.dozone.wehagopro.domain.TokenDto;

import java.util.Map;

public interface MemberService {
    public String signUp(MemberSignUpRequestDto requestDto) throws Exception;
    public TokenDto login(Map<String, String> members);
    public String logout(String accessToken, String refreshToken);
    public TokenDto reissueAccessToken(TokenDto tokenDto);
}