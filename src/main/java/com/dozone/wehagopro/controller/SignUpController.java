package com.dozone.wehagopro.controller;

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
}
