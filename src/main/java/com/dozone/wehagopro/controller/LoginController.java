package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.domain.Item2;
import com.dozone.wehagopro.domain.Login;
import com.dozone.wehagopro.domain.LoginForm;
import com.dozone.wehagopro.domain.ShortLinkLoginDto;
import com.dozone.wehagopro.repository.login.Loginrepository;
import com.dozone.wehagopro.repository.mybatis.MyBatisItemRepository;
import com.dozone.wehagopro.service.loginservice.LoginService1;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LoginController {
    @Autowired
    LoginService1 service;
    @Autowired
    MyBatisItemRepository repository;


    @PostMapping("/login")
    public Item2 test1(@RequestBody LoginForm loginForm) {
        System.out.println("로그인");
        String userid = loginForm.getUserid();
        System.out.println("userid = " + userid);
        String password = loginForm.getPassword();
        System.out.println("password = " + password);

        Item2 qqq = repository.findidpw(userid, password);
        System.out.println(qqq.getT_user_id() + qqq.getT_user_password());
        return qqq;
    }

    @PostMapping("/findid1")
    public Login test2(@RequestBody Login login) {
        System.out.println("아이디찾기1");
        String id = login.getT_user_id();
        System.out.println("id = " + id);
        String email = login.getT_user_email();
        System.out.println("email = " + email);
        String name = login.getT_user_name();
        System.out.println("name = " + name);

        Login findidemail = repository.findidemail(email, name);
        System.out.println(findidemail.getT_user_id() + findidemail.getT_user_email() + findidemail.getT_user_name());
        return findidemail;
    }


    @PostMapping("/findid2")
    public Login test3(@RequestBody Login login) {
        System.out.println("아이디찾기2");
        String id = login.getT_user_id();
        System.out.println("id = " + id);
        String phone = login.getT_user_phone();
        System.out.println("phone = " + phone);
        String name = login.getT_user_name();
        System.out.println("name = " + name);

        Login findidphone = repository.findidphone(id, phone, name);
        System.out.println(findidphone.getT_user_id() + findidphone.getT_user_phone() + findidphone.getT_user_name());
        return findidphone;
    }

    @PostMapping("/findpw")
    public Login test4(@RequestBody Login login) {
        System.out.println("비밀번호찾기1");
        String id = login.getT_user_id();
        System.out.println("id = " + id);
        String email = login.getT_user_email();
        System.out.println("email = " + email);

        Login findpwemail = repository.findpwemail(id, email);
        System.out.println(findpwemail.getT_user_password() + findpwemail.getT_user_email() + findpwemail.getT_user_id());
        return findpwemail;
    }

//    @PostMapping("findpw2")
//    public Login test5(@RequestBody Login login){
//        System.out.println("비밀번호찾기2");
//        String id = login.getT_user_id();
//        System.out.println("id = " + id);
//        String phone = login.getT_user_phone();
//        System.out.println("phone = " + phone);
//
//        Login findpwphone = repository.findpwphone(id, phone);
//        System.out.println(findpwphone.getT_user_password() + findpwphone.getT_user_phone() + findpwphone.getT_user_id());
//        return findpwphone;
//    }

    @PostMapping("updatepw")
    public int test6(@RequestBody Login login) {
        System.out.println("비밀번호재설정");
        String id = login.getT_user_id();
        System.out.println("id = " + id);
        String npw = login.getT_user_new_password();
        System.out.println("newpw = " + npw);

        int updatepw = repository.updatepw(id, npw);
//        System.out.println("새비밀번호 = " + updatepw.getT_user_new_password() + "유저 id = " +  updatepw.getT_user_id());
        return updatepw;
    }


    @Autowired
    Loginrepository loginrepository;

    @ResponseBody
    @GetMapping("/l/{llink}")
    public ShortLinkLoginDto invite(@PathVariable String llink) {
        String emNo = llink.substring(2);
        int num = Integer.parseInt(emNo);
        Integer integerState = service.employeeStateCheck(num);
        if (integerState != null) {
            System.out.println("empo 널 아님11");
            if (integerState.intValue() == 1) {
                System.out.println("회원 대기상태11");
                ShortLinkLoginDto shortLinkDto = service.findRedirectLink(num);
                Date findSqlDate = shortLinkDto.getShortLinkDeadLine();
                LocalDate localDate = shortLinkDto.getShortLinkDeadLine().toLocalDate();
                if (findSqlDate == null) {
                    System.out.println("SQL 값이 없음11");
                } else if (findSqlDate.toLocalDate().compareTo(LocalDate.now()) < 0) {
                    System.out.println("시간 만료11");
                } else {
                    shortLinkDto.setEmpNo(num);
                    return shortLinkDto;
                }
            }
            System.out.println("회원 대기 상태 아님11");
        }
        return null;
    }
}






