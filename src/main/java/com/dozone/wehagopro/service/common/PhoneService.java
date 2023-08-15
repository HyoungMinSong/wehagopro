package com.dozone.wehagopro.service.common;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PhoneService {

    public String sendRandomMessage(String tel) {
        System.out.println("tel : "+tel);
        Naver_Sens_V2 message = new Naver_Sens_V2();
        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 6; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }
        System.out.println("회원가입 문자 인증 => " + numStr);
        tel.replace("\"","");

        message.send_msg(tel, numStr);

        return numStr;
    }

}
