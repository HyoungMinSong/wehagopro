package com.dozone.wehagopro.repository.login;

import com.dozone.wehagopro.domain.ShortLinkLoginDto;
import com.dozone.wehagopro.repository.mybatis.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Loginrepository {

    @Autowired
    ItemMapper mapper;

    public Integer selectEmployeeStateCheck(int empNo){
        System.out.println("repsitoryempNo = " + empNo);
        return mapper.selectEmployeeStateCheck(empNo);
    }

    public ShortLinkLoginDto selectShortLinkDeadLine(int empNo){
        System.out.println("shortLinkDeadLine = " + empNo);
        return mapper.selectShortLinkDeadLine(empNo);
    }

    }

