package com.dozone.wehagopro.service.signUp;

import com.dozone.wehagopro.repository.signUp.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    SignUpRepository repository;

    public String checkId(String id){
        System.out.println("serviceid = " + id);
        return repository.idCheck(id);
    }
}
