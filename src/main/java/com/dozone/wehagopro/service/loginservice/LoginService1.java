package com.dozone.wehagopro.service.loginservice;

import com.dozone.wehagopro.domain.ShortLinkLoginDto;
import com.dozone.wehagopro.domain.signUp.ShortLinkSignUpDto;
import com.dozone.wehagopro.repository.login.Loginrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Service
public class LoginService1 {

    @Autowired
    Loginrepository loginrepository;
    private  final PasswordEncoder passwordEncoder;

    public Integer employeeStateCheck(int empNo){
        System.out.println("serviceEmpNo = " + empNo);
        return loginrepository.employeeStateCheck(empNo);
    }

    public ShortLinkLoginDto findRedirectLink(int empNo){
        System.out.println("serviceEmpNo = " + empNo);
        return loginrepository.findShortLinkByEmpNo(empNo);
    }
}
