package com.dozone.wehagopro.service.loginservice;

import com.dozone.wehagopro.domain.ShortLinkLoginDto;
import com.dozone.wehagopro.domain.UserNoPwDto;
import com.dozone.wehagopro.domain.signUp.Loginupdatedto;
import com.dozone.wehagopro.domain.signUp.ShortLinkSignUpDto;
import com.dozone.wehagopro.repository.login.Loginrepository;
import com.dozone.wehagopro.repository.mybatis.MyBatisItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Service
public class LoginService1 {

    @Autowired
    Loginrepository loginrepository;

    @Autowired
    MyBatisItemRepository itemRepository;
    @Autowired
    private  final PasswordEncoder passwordEncoder;

    public Integer employeeStateCheck(int empNo){
        System.out.println("employeeStateCheck = " + empNo);
        return loginrepository.employeeStateCheck(empNo);
    }

    public ShortLinkLoginDto shortLinkDeadLine(int empNo){
        System.out.println("shortLinkDeadLine = " + empNo);
        return loginrepository.shortLinkDeadLine(empNo);
    }

    public void findusernopwbyid(Loginupdatedto dto){
        System.out.println("서비스매소드 실행"+dto.getT_user_id());
        UserNoPwDto noPwDto = itemRepository.findusernopwbyid(dto.getT_user_id());
        System.out.println("아이디랑 패스워드로 유저넘버를 select 한 값 : " + noPwDto);
        if (!passwordEncoder.matches(dto.getT_user_password(), noPwDto.getT_user_password())){
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
        itemRepository.updateusernobyempno(noPwDto.getT_user_no(), dto.getT_employee_no());
        System.out.println("찾은 유저넘버로 employee num을 업데이트 실행");

    }



}
