package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.UserDto;
import com.dozone.wehagopro.domain.UserCompanyDto;
import com.dozone.wehagopro.domain.UserServiceDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<UserDto> findByUserId(@Param("userId") String userId);
    List<UserCompanyDto> getUserCompanyList(@Param("userId") String userId);
    List<UserServiceDto> getUserServiceList(@Param("userId") String userId);
    void updateUser(@Param("userPhoto") String userPhoto, @Param("userName") String userName,
                   @Param("userId") String userId, @Param("userEmail") String userEmail, @Param("userPhone") String userPhone);
    boolean updateUserPassword(@Param("userId") String userId, @Param("newPassword") String newPassword);

    String findByUserEmail(@Param("userEmail") String userEmail);
    String findByUserPhone(@Param("userPhone") String userPhone);
}