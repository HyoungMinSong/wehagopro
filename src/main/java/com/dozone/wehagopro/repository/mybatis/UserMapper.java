package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.NoticeSelectDto;
import com.dozone.wehagopro.domain.UserDto;
import com.dozone.wehagopro.domain.UserCompanyDto;
import com.dozone.wehagopro.domain.UserServiceDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<UserDto> selectByUserId(@Param("userId") String userId);
    List<UserCompanyDto> selectUserCompanyList(@Param("userId") String userId);
    List<UserServiceDto> selectUserServiceList(@Param("userId") String userId);
    void updateUser(@Param("userPhoto") String userPhoto, @Param("userName") String userName,
                   @Param("userId") String userId, @Param("userEmail") String userEmail, @Param("userPhone") String userPhone);
    boolean updateUserPassword(@Param("userId") String userId, @Param("newPassword") String newPassword);

    String selectByUserEmail(@Param("userEmail") String userEmail);
    String selectByUserPhone(@Param("userPhone") String userPhone);
    List<NoticeSelectDto> selectNoticeLimit5(@Param("companyNo") int companyNo);
}