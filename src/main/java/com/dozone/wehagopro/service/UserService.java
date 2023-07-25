package com.dozone.wehagopro.service;

import com.dozone.wehagopro.domain.TokenDto;
import com.dozone.wehagopro.domain.UserInfoDto;

public interface UserService {
//    public UserDto signUp(UserSignUpRequestDto userSignUpRequestDto) throws Exception;
    public TokenDto login(String id, String password);
    public boolean logout(TokenDto tokenDto);
    public TokenDto reissueAccessToken(String refreshToken);
    public UserInfoDto getUserInfo(String accessToken);
}