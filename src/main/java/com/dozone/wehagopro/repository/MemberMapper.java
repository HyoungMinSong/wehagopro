package com.dozone.wehagopro.repository;

import com.dozone.wehagopro.domain.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface MemberMapper {
    @Select("select * from member where id = #{username}")
    Optional<Member> findByMembername(@Param("username") String username);

    @Insert("insert into member values(#{id}, #{password}, #{name}, #{role}, #{enabled})")
    void insertMember(@Param("id") String id,
                      @Param("password") String password,
                      @Param("name") String name,
                      @Param("role") String role,
                      @Param("enabled") char enabled);

}
