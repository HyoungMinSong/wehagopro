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

    public Login findidemail(String username, String email, String id){
        return itemMapper.findidemail(username, email, id);
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
}
