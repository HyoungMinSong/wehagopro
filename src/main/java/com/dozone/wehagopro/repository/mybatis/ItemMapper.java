package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.*;
import com.dozone.wehagopro.domain.signUp.ShortLinkSignUpDto;
import com.dozone.wehagopro.repository.ItemSearchCond;
import com.dozone.wehagopro.repository.ItemUpdateDto;
import lombok.extern.java.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {
    Item2 findidpw(String param1, String param2);

    Login findidemail(@Param("email") String email, @Param("name") String name);

    Login findidphone(String param1, String param2);

    Login findpwemail(String param1, String param2);

    Login findpwphone(String param1, String param2);

    int updatepw(String param1, String param2);

    Integer employeeStateCheck(int empNo);

    ShortLinkLoginDto shortLinkDeadLine(int empNo);

    UserNoPwDto findusernopwbyid(String t_user_id);
  
    void updateusernobyempno(int param1, int param2);

    void createNotice(@Param("t_user_name") String t_user_name, @Param("t_company_no") int t_company_no, @Param("t_notice_title") String t_notice_title, @Param("t_notice_content") String t_notice_content);

    List<NoticeSelectDto> selectNotice(int t_company_no);


}
