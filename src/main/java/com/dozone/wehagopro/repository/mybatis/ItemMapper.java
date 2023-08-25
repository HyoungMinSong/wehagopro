package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {
    Item2 selectIdPw(String param1, String param2);

    Login selectIdEmail(@Param("email") String email, @Param("name") String name);

    Login selectIdPhone(String param1, String param2);

    Login selectPwEmail(String param1, String param2);

    Login selectPwPhone(String param1, String param2);

    int updatePw(String param1, String param2);

    Integer selectEmployeeStateCheck(int empNo);

    ShortLinkLoginDto selectShortLinkDeadLine(int empNo);

    UserNoPwDto selectUserNoPw(String t_user_id);
  
    void updateUserNo(int param1, int param2);

    void insertNotice(@Param("t_user_name") String t_user_name, @Param("t_company_no") int t_company_no, @Param("t_notice_title") String t_notice_title, @Param("t_notice_content") String t_notice_content);

    List<NoticeSelectDto> selectNotice(int t_company_no);

    void updateNotice(@Param("t_notice_title") String t_notice_title, @Param("t_notice_content") String t_notice_content, @Param("t_notice_no") int t_notice_no);

    void deleteNotice(int t_notice_no);

    void deleteUser(@Param("t_user_no") int t_user_no, @Param("t_user_delete_reason") String t_user_delete_reason);

}
