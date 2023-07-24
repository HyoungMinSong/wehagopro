package com.dozone.wehagopro.repository.mybatis;

import org.apache.ibatis.annotations.*;

@Mapper
public interface RefreshTokenMapper {
    @Insert("insert into t_jwt values(#{userId}, #{token}, #{expiration})")
    void insertToken(@Param("userId") String userId,
                      @Param("token") String token,
                      @Param("expiration") Long expiration);

    @Select("select t_refresh_token from t_jwt where t_user_id = #{userId}")
    String getToken(@Param("userId") String userId);

    @Update("update t_jwt set t_refresh_token = #{token}, t_expiration = #{expiration} where t_user_id = #{userId}")
    void updateToken(@Param("userId") String userId,
                     @Param("token") String token,
                     @Param("expiration") Long expiration);

    @Delete("delete from t_jwt where t_user_id = #{userId}")
    void deleteToken(@Param("userId") String userId);
}
