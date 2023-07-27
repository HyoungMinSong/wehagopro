package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.domain.Item2;
import com.dozone.wehagopro.domain.Login;
import com.dozone.wehagopro.domain.LoginForm;
import com.dozone.wehagopro.repository.mybatis.MyBatisItemRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LoginController {
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
    public Login test2(@RequestBody Login login){
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
    public Login test3(@RequestBody Login login){
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

    @PostMapping("updatepw")
    public int test6(@RequestBody Login login){
        System.out.println("비밀번호재설정");
        String id = login.getT_user_id();
        System.out.println("id = " + id);
        String pw = login.getT_user_password();
        System.out.println("pw = " + pw);
        String npw = login.getT_user_new_password();
        System.out.println("newpw = " + npw);
        String npwcheck = login.getT_user_new_password_check();
        System.out.println("newpwcheck = " + npwcheck);

        int updatepw = repository.updatepw(npw, id, pw);
//        System.out.println("새비밀번호 = " + updatepw.getT_user_new_password() + "유저 id = " +  updatepw.getT_user_id());
        return updatepw;
    }

//    @PostMapping("findpw1")
//    public Login test4(@RequestBody Login login){
//        System.out.println("비밀번호찾기1");
//        String pw = login.getT_user_password();
//        System.out.println("pw = " + pw);
//        String email = login.getT_user_email();
//        System.out.println("email = " + email);
//        String id = login.getT_user_id();
//        System.out.println("id = " + id);
//
//        Login findpwemail = repository.findpwemail(pw, email, id);
//        System.out.println(findpwemail.getT_user_password() + findpwemail.getT_user_email() + findpwemail.getT_user_id());
//        return findpwemail;
//    }
//
//    @PostMapping("findpw2")
//    public Login test5(@RequestBody Login login){
//        System.out.println("비밀번호찾기2");
//        String pw = login.getT_user_password();
//        System.out.println("pw = " + pw);
//        String phone = login.getT_user_phone();
//        System.out.println("phone = " + phone);
//        String id = login.getT_user_id();
//        System.out.println("id = " + id);
//
//        Login findpwphone = repository.findpwphone(pw, phone, id);
//        System.out.println(findpwphone.getT_user_password() + findpwphone.getT_user_phone() + findpwphone.getT_user_id());
//        return findpwphone;
//    }


}






