package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.domain.signUp.ShortLinkSignUpDto;
import com.dozone.wehagopro.domain.signUp.SignUpDto;
import com.dozone.wehagopro.domain.signUp.User;
import com.dozone.wehagopro.service.common.MailService;
import com.dozone.wehagopro.service.signUp.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

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

    @ResponseBody
    @PostMapping("/mailConfirm")
    String mailConfirm(@RequestBody Map<String, String> body) throws Exception {
        String email = body.get("email");
        System.out.println("입력한 이메일 : " + email);
        String code = mailService.sendSimpleMessage(email);
        System.out.println("인증 코드 : " + code);
        return code;
    }
    @GetMapping("/s/{slink}")
    public String greetUser(@PathVariable String slink) { // 앞 랜덤 두글자 + 직원번호 (나는 유저를 만들고 유저 no를 넣어나야함.
        String emNo = slink.substring(2);
        int num = Integer.parseInt(emNo);
        ShortLinkSignUpDto shortLinkDto = service.findRedirectLink(num);
        Date findSqlDate = shortLinkDto.getShortLinkDeadLine();
        LocalDate localDate = shortLinkDto.getShortLinkDeadLine().toLocalDate();
        if (findSqlDate == null){
            System.out.println("SQL 값이 없음");
        } else if (findSqlDate.toLocalDate().compareTo(LocalDate.now()) <0) {
            System.out.println("시간 만료");
        } else {
//            유저 인서트 하면됨와 나온 유저 인서트를 임플로이 인서트를 하면 된다.

            // 이메일 버튼을 누르면 바로 백엔드로 올껀지 아니면 리액트로 올껀지 내일 정하기.
        }

        return "Hello, "  + "!";


}
