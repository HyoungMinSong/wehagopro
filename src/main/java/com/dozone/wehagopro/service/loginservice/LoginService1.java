package com.dozone.wehagopro.service.loginservice;

import com.dozone.wehagopro.domain.ShortLinkLoginDto;
import com.dozone.wehagopro.domain.signUp.ShortLinkSignUpDto;
import com.dozone.wehagopro.repository.login.Loginrepository;
import com.dozone.wehagopro.repository.mybatis.MyBatisItemRepository;
import com.dozone.wehagopro.repository.signUp.SignUpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoginService1 {

    @Autowired
    Loginrepository loginrepository;
    MyBatisItemRepository itemRepository;
    private  final PasswordEncoder passwordEncoder;
    @Autowired
    SignUpRepository repository;

    public Integer employeeStateCheck(int empNo){
        System.out.println("employeeStateCheck = " + empNo);
        return loginrepository.employeeStateCheck(empNo);
    }

    public ShortLinkLoginDto shortLinkDeadLine(int empNo){
        System.out.println("shortLinkDeadLine = " + empNo);
        return loginrepository.shortLinkDeadLine(empNo);
    }

    public void findusernobyidpw(String userid, String password, int empNo){
        System.out.println("서비스매소드 실행");
        int userno = itemRepository.findusernobyidpw(userid, password);
        System.out.println("아이디랑 패스워드로 유저넘버를 select 한 값 : " + userno);
        itemRepository.updateusernobyempno(userno, empNo);
        System.out.println("찾은 유저넘버로 employee num을 업데이트 실행");

        int t_package_no = itemRepository.findPackageByEmpNo(empNo);
        List<Integer> serviceNo = repository.findServiceNoByPackageNo(t_package_no);
        for (int a:
                serviceNo) {
            System.out.println("a = " + a);
        }
        List<Integer> serviceNoByServiceFree = repository.findServiceNoByServiceFree();
        serviceNo.addAll(serviceNoByServiceFree);
        for (int num:
                serviceNo) {
            System.out.println("num = " + num);
            repository.signUpServicePublished(empNo, num);
        }
    }



}
