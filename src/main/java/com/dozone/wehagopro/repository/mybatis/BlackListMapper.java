package com.dozone.wehagopro.repository.mybatis;

import org.apache.ibatis.annotations.*;

@Mapper
public interface BlackListMapper {
    @Insert("insert into t_jwt_blacklist values(#{token}, #{expiration})")
    void insertBlackList(@Param("token") String token, @Param("expiration") Long expiration);

    @Select("select t_access_token from t_jwt_blacklist where t_access_token = #{token}")
    String hasBlackList(@Param("token") String token);

    @Delete("delete from t_jwt_blacklist where t_access_token = #{token}")
    boolean deleteBlackList(@Param("token") String token);
}