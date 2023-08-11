package com.dozone.wehagopro.service;

import com.dozone.wehagopro.domain.PhotoDto;
import com.dozone.wehagopro.domain.TokenDto;
import com.dozone.wehagopro.domain.UserInfoDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
//    public UserDto signUp(UserSignUpRequestDto userSignUpRequestDto) throws Exception;
    public TokenDto login(String id, String password);
    public boolean logout(TokenDto tokenDto);
//    public TokenDto reissueAccessToken(String refreshToken);
    public UserInfoDto getUserInfo(Authentication authentication);
    public PhotoDto updateUserInfo(Authentication authentication,
                                   MultipartFile file, boolean isDelete, String name, String id, String email, String phone);
    public boolean updateUserPassword(Authentication authentication, String id, String currentPassword, String newPassword);
}