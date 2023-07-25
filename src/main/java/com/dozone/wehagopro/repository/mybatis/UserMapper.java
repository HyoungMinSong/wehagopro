package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.UserDTO;
import com.dozone.wehagopro.domain.UserCompanyDto;
import com.dozone.wehagopro.domain.UserServiceDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<UserDTO> findByUserId(@Param("userId") String userId);
    List<UserCompanyDto> getUserCompanyList(@Param("userId") String userId);
    List<UserServiceDto> getUserServiceList(@Param("userId") String userId);
}