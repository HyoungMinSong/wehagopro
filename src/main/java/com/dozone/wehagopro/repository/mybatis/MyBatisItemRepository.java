package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.Item2;
import com.dozone.wehagopro.domain.Login;
import com.dozone.wehagopro.domain.UserNoPwDto;
import com.dozone.wehagopro.repository.ItemSearchCond;
import com.dozone.wehagopro.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository{

    private final ItemMapper itemMapper;
    private final PasswordEncoder passwordEncoder;

    public Item2 findidpw(String userid, String password) {
        return itemMapper.findidpw(userid,password);
    }

    public Login findidemail(String email, String name){
        return itemMapper.findidemail( email, name);
    }

    public Login findidphone(String username, String phone, String id){
        return itemMapper.findidphone(username, phone, id);
    }

    public Login findpwemail(String id, String email){
        return itemMapper.findpwemail(id, email);
    }

    public Login findpwphone(String id, String phone){
        return itemMapper.findpwphone(id, phone);
    }

    public int updatepw(String id, String npw){
        return itemMapper.updatepw(id, passwordEncoder.encode(npw));
    }

    public UserNoPwDto findusernopwbyid(String t_user_id)
    {
        System.out.println("t_user_id"+t_user_id);
        return itemMapper.findusernopwbyid(t_user_id);}

    public void updateusernobyempno(int t_user_no, int t_employee_no)
    {itemMapper.updateusernobyempno(t_user_no,t_employee_no);}

    public int findPackageByEmpNo(int t_employee_no){
        return itemMapper.findPackageByEmpNo(t_employee_no);
    }
}
