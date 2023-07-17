package com.dozone.wehagopro.repository.signUp;

import com.dozone.wehagopro.repository.mybatis.SignUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SignUpRepository {

    @Autowired
    SignUpMapper mapper;

    public String idCheck(String id){
        System.out.println("repsitoryid = " + id);
        return mapper.idCheck(id);
    }

}
