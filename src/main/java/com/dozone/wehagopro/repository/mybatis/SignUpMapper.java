package com.dozone.wehagopro.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignUpMapper {

    String idCheck(String id);
}
