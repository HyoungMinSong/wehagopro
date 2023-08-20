package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.domain.*;
import com.dozone.wehagopro.domain.signUp.Loginupdatedto;
import com.dozone.wehagopro.repository.login.Loginrepository;
import com.dozone.wehagopro.repository.mybatis.MyBatisItemRepository;
import com.dozone.wehagopro.service.common.Loggable;
import com.dozone.wehagopro.service.common.PhoneService;
import com.dozone.wehagopro.service.loginservice.LoginService1;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LoginController {
    @Autowired
    LoginService1 service;
    @Autowired
    MyBatisItemRepository repository;
    @Autowired
    PhoneService memberService;


//    @PostMapping("/login")
//    public Item2 test1(@RequestBody LoginForm loginForm) {
//        System.out.println("로그인");
//        String userid = loginForm.getUserid();
//        System.out.println("userid = " + userid);
//        String password = loginForm.getPassword();
//        System.out.println("password = " + password);
//
//        Item2 qqq = repository.findidpw(userid, password);
//        System.out.println(qqq.getT_user_id() + qqq.getT_user_password());
//        return qqq;
//    }

    @PostMapping("/findid1")
    public Login test2(@RequestBody Login login) {
        //PostMapping 으로 받으면 @RequestBoday를 이용해서 객체형식으로 값을 받아야한다
        System.out.println("아이디찾기1");
        String id = login.getT_user_id();
        //login 객체의 자료형인 Login의 get함수로 login에 담긴 id값을 찾아서 id에 담음
        System.out.println("id = " + id);
        String email = login.getT_user_email();
        //login 객체의 자료형인 Login의 get함수로 login에 담긴 email값을 찾아서 email에 담음
        System.out.println("email = " + email);
        String name = login.getT_user_name();
        //login 객체의 자료형인 Login의 get함수로 login에 담긴 name값을 찾아서 name에 담음
        System.out.println("name = " + name);

        Login findidemail = repository.findidemail(email, name);
        //findidemail 객체에 repository의 findidemail 매소드를 email,name 을 매개변수로 담아서 실행한 값을 담는다
        System.out.println(findidemail);
        return findidemail;
        //select문을 사용할거라 리턴을 해서 db 갔다온 값을 리턴해야한다
        //여기다가는 리턴자료형을 쓰는게 아니고 뭘 보여줄건지 매소드를 실행했을때 결과가 리턴값이다
        //리턴  자료형과 맞는 객체를 반환한다
    }


    @PostMapping("/findid2")
    public Login test3(@RequestBody Login login) {

        System.out.println("아이디찾기2");
        String phone = login.getT_user_phone();
        System.out.println("phone = " + phone);
        String name = login.getT_user_name();
        System.out.println("name = " + name);

        Login findidphone = repository.findidphone(name, phone);
        System.out.println(findidphone.getT_user_phone() + findidphone.getT_user_name());
        return findidphone;
    }

    @PostMapping("/phoneAuth")
    public String phoneAuth(@RequestBody String email) {

        System.out.println("email : "+email);
        String code = memberService.sendRandomMessage(email);

        return code;
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

    @PostMapping("findpw2")
    public Login test5(@RequestBody Login login){
        System.out.println("비밀번호찾기2");
        String id = login.getT_user_id();
        System.out.println("id = " + id);
        String phone = login.getT_user_phone();
        System.out.println("phone = " + phone);

        Login findpwphone = repository.findpwphone(id, phone);
        System.out.println(findpwphone.getT_user_password() + findpwphone.getT_user_phone() + findpwphone.getT_user_id());
        return findpwphone;
    }

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
    public ShortLinkLoginDto invite1(@PathVariable String llink) {
        String emNo = llink.substring(2);
        int num = Integer.parseInt(emNo);
        Integer integerState = service.employeeStateCheck(num);
        System.out.println("/링크 en뒤에 추출한 값 : " + num);
        System.out.println("integerState: " + integerState);
        if (integerState != 1) {
            System.out.println("1 이다(대기상태)");
            return null;
//        } else if (integerState.intValue() == -1) {
//            System.out.println("-1 이다(퇴사상태)");
//        } else if (integerState.intValue() == 0) {
//            System.out.println("0 이다(미가입상태");
//        } else if (integerState.intValue() == 2) {
//            System.out.println("2 이다(사용중인상태)");
//        } else {
//            System.out.println("3 이다(중지상태");
        }

        ShortLinkLoginDto shortLinkDto = service.shortLinkDeadLine(num);
        Date findSqlDate = shortLinkDto.getShortLinkDeadLine();
        if (findSqlDate == null) {
            System.out.println("DeadLine 값이 없음");
            return null;
        } else if (findSqlDate.toLocalDate().compareTo(LocalDate.now()) < 0) {
            System.out.println("DeadLine 시간이 만료됨");
            return null;
        } else {
            shortLinkDto.setEmpNo(num);
            System.out.println("접속이 유효하다");
            return shortLinkDto;
        }
    }


    @PostMapping("/updateinvite")
    public void updateuserno(@RequestBody Loginupdatedto loginupdatedto){
        System.out.println("loginupdatedto = " + loginupdatedto);
        service.findusernopwbyid(loginupdatedto);
        System.out.println("컨트롤러업데이트메소드실행");
        System.out.println("loginupdatedto = " + loginupdatedto);
    }

    @Loggable
    @PostMapping("/createNotice")
    public void createNotice(@RequestBody NoticeDto noticeDto){
        System.out.println("noticeDto = " + noticeDto);
        service.createNotice(noticeDto);

    }

    @GetMapping("/selectNotice")
    public List<NoticeSelectDto> selectNotice(int t_company_no){
        System.out.println("t_company_no = " + t_company_no);
        return service.selectNotice(t_company_no);
    }

    @PostMapping("/updateNotice")
    public void updateNotice(@RequestBody NoticeSelectDto noticeSelectDto){
        System.out.println("noticeSelectDto = " + noticeSelectDto);
        service.updateNotice(noticeSelectDto);
    }

    @PostMapping("/deleteNotice")
    public void deleteNotice(@RequestBody NoticeSelectDto noticeSelectDto){
        System.out.println("noticeSelectDto = " + noticeSelectDto);
        service.deleteNotice(noticeSelectDto);
    }

    @PutMapping("/withDrawal")
    public void withdrawal(@RequestBody WithdrawalDto withdrawalDto){
        System.out.println("withdrawalDto = " + withdrawalDto);
        service.withdrawal(withdrawalDto);
    }

}










