package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.Item2;
import com.dozone.wehagopro.domain.Login;
import com.dozone.wehagopro.domain.ShortLinkLoginDto;
import com.dozone.wehagopro.domain.UserNoPwDto;
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

    Login findidphone(String param1, String param2, String param3);

    Login findpwemail(String param1, String param2);

    Login findpwphone(String param1, String param2);

    int updatepw(String param1, String param2);

    Integer employeeStateCheck(int empNo);

    ShortLinkLoginDto shortLinkDeadLine(int empNo);

    UserNoPwDto findusernopwbyid(String t_user_id);
  
    void updateusernobyempno(int param1, int param2);
}
