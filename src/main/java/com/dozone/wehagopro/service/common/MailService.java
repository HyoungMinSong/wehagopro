package com.dozone.wehagopro.service.common;

import com.dozone.wehagopro.domain.OrganizationSelectedDto;
import com.dozone.wehagopro.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

@Service
public class MailService {

    private final OrganizationRepository organizationRepository;
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender, OrganizationRepository organizationRepository) {
        this.javaMailSender = javaMailSender;
        this.organizationRepository = organizationRepository;
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

    private String ePw; // 인증번호

    // 메일 내용 작성
    public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {
//		System.out.println("보내는 대상 : " + to);
//		System.out.println("인증 번호 : " + ePw);

        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to);// 보내는 대상
        message.setSubject("WEHAGO 이메일 인증");// 제목

        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요</h1>";
        msgg += "<h1> WEHAGO 입니다.</h1>";
        msgg += "<br>";
        msgg += "<p>아래 코드를 진행중인 인증 페이지로 돌아가 입력해주세요.<p>";
        msgg += "<br>";
        msgg += "<p>항상 당신의 꿈을 응원합니다. 감사합니다!<p>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>인증 코드입니다.</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "CODE : <strong>";
        msgg += ePw + "</strong><div><br/> "; // 메일에 인증번호 넣기
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");// 내용, charset 타입, subtype
        // 보내는 사람의 이메일 주소, 보내는 사람 이름
        message.setFrom(new InternetAddress("wehagoAdmin@wehago.com", "WEHAGO_ADMIN"));// 보내는 사람

        return message;
    }

    // 랜덤 인증 코드 전송
    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤, rnd 값에 따라서 아래 switch 문이 실행됨

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    // a~z (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    // A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }

        return key.toString();
    }

    // 메일 발송
    // sendSimpleMessage 의 매개변수로 들어온 to 는 곧 이메일 주소가 되고,
    // MimeMessage 객체 안에 내가 전송할 메일의 내용을 담는다.
    // 그리고 bean 으로 등록해둔 javaMail 객체를 사용해서 이메일 send!!
    public String sendSimpleMessage(String to) throws Exception {
        ePw = createKey(); // 랜덤 인증번호 생성

        MimeMessage message = createMessage(to); // 메일 발송
        try {// 예외처리
            javaMailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }

        return ePw; // 메일로 보냈던 인증 코드를 서버로 반환
    }

    // 직원 초대 이메일
    @Transactional
    public void sendMailToEmployee(String employer, List<OrganizationSelectedDto> dto) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        RegisterMail registerMail = new RegisterMail();
        String compName;
        for(OrganizationSelectedDto empl : dto){
            compName = organizationRepository.selectCompanyName(empl.getT_company_no());
            empl.setT_company_name(compName);
            helper.setTo(empl.getT_user_email());
            helper.setSubject(empl.getT_user_name()+"님을 WEHAGO(으)로 초대합니다.");
            helper.setText(registerMail.mailToEmployee(employer, empl.getT_user_name(), empl.getT_company_name(), empl.getT_organization_name(), empl.getT_employee_duty(), empl.getT_employee_position(), empl.getT_employee_no().toString()), true);
            javaMailSender.send(mimeMessage);
            organizationRepository.updateReceivedMailEmployee(empl.getT_employee_no());
            organizationRepository.updateReceivedMailShortlink(empl.getT_employee_no());
        }
    }

    }

