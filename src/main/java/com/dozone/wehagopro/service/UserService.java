package com.dozone.wehagopro.service;

import com.dozone.wehagopro.domain.NoticeSelectDto;
import com.dozone.wehagopro.domain.PhotoDto;
import com.dozone.wehagopro.domain.TokenDto;
import com.dozone.wehagopro.domain.UserInfoDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
//    public UserDto signUp(UserSignUpRequestDto userSignUpRequestDto) throws Exception;
    public TokenDto findLogin(String id, String password);
    public boolean findLogout(TokenDto tokenDto);
//    public TokenDto reissueAccessToken(String refreshToken);
    public UserInfoDto findUserInfo(Authentication authentication);
    public PhotoDto modifyUserInfo(MultipartFile file, boolean isDelete, String name, String id, String email, String phone);
    public boolean modifyUserPassword(String id, String currentPassword, String newPassword);
    public List<NoticeSelectDto> findNoticeLimit5(int companyNo);
}