package com.dozone.wehagopro.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        SignUpMail signUpMail = new SignUpMail();

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(signUpMail.exampleMail("고용자","직장인","회사이름","테스트부서","과장","깃깃깃","/signup","/login"), true);

        javaMailSender.send(mimeMessage);
    }
}