package com.dozone.wehagopro.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private String t_user_name;
    private String t_user_email;
    private String t_user_photo_path;
    private List<UserCompanyDto> userCompanyDtoList;
    private List<UserServiceDto> userServiceDtoList;
}
