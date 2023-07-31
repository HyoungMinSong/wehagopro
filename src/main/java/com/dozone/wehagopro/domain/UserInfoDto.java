package com.dozone.wehagopro.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private UserDto userDto;
    private List<UserCompanyDto> userCompanyDtoList;
    private List<UserServiceDto> userServiceDtoList;
}
