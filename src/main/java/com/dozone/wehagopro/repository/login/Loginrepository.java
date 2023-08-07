package com.dozone.wehagopro.repository.login;

import com.dozone.wehagopro.domain.ShortLinkLoginDto;
import com.dozone.wehagopro.domain.signUp.ShortLinkSignUpDto;
import com.dozone.wehagopro.repository.mybatis.ItemMapper;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Loginrepository {

    @Autowired
    ItemMapper mapper;

    public Integer employeeStateCheck(int empNo){
        System.out.println("repsitoryempNo = " + empNo);
        return mapper.employeeStateCheck(empNo);
    }

    public ShortLinkLoginDto shortLinkDeadLine(int empNo){
        System.out.println("shortLinkDeadLine = " + empNo);
        return mapper.shortLinkDeadLine(empNo);
    }

    }

