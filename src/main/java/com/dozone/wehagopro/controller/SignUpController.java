package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.domain.signUp.SignUpDto;
import com.dozone.wehagopro.domain.signUp.User;
import com.dozone.wehagopro.service.signUp.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class SignUpController {

    @Autowired
    SignUpService service;

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
}
