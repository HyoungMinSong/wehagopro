package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.Item2;
import com.dozone.wehagopro.domain.Login;
import com.dozone.wehagopro.repository.ItemSearchCond;
import com.dozone.wehagopro.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository{

    private final ItemMapper itemMapper;


    public Item2 findidpw(String userid, String password) {
        return itemMapper.findidpw(userid,password);
    }

    public Login findidemail(String email, String name){
        return itemMapper.findidemail( email, name);
    }

    public Login findidphone(String username, String phone, String id){
        return itemMapper.findidphone(username, phone, id);
    }

    public Login findpwemail(String username, String email, String pw){
        return itemMapper.findpwemail(username, email, pw);
    }

    public Login findpwphone(String username, String phone, String pw){
        return itemMapper.findpwphone(username, phone, pw);
    }

    public int updatepw(String npw, String id, String pw){
        return itemMapper.updatepw(npw, id, pw);
    }
}
