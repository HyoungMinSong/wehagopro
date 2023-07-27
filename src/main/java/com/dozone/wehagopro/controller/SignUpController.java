package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.domain.signUp.SignUpDto;
import com.dozone.wehagopro.domain.signUp.User;
import com.dozone.wehagopro.service.common.MailService;
import com.dozone.wehagopro.service.signUp.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class SignUpController {

    @Autowired
    SignUpService service;
    @Autowired
    MailService mailService;

    @ResponseBody
    @PostMapping("/idcheck")
    public String idCheck(@RequestBody User user) {
        System.out.println("id = " + user.getId());
        String result = service.checkId(user.getId());
        return result;
    }

    @ResponseBody
    @PostMapping("/signupinsert")
    public String signUpInsert(@RequestBody SignUpDto dto) {
        System.out.println("dto = " + dto);
        service.signUpInsert(dto);
        return "컨트롤라성공";
    }

    @ResponseBody
    @PostMapping("/companycheck")
    public String companyCheck(@RequestBody User user) {
        System.out.println("id = " + user.getCompanyName());
        String result = service.checkCompanyName(user.getCompanyName());
        return result;
    }

    @PostMapping("/testmail")
    public void mailSend() throws MessagingException {
        String mail = "shmin11@naver.com";
        String title = "테스트";
        String main = "테스트 메인";
        mailService.sendEmail(mail,title,main);
    }
}
