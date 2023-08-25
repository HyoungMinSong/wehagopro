package com.dozone.wehagopro.service.loginservice;

import com.dozone.wehagopro.domain.*;
import com.dozone.wehagopro.domain.signUp.Loginupdatedto;
import com.dozone.wehagopro.repository.login.Loginrepository;
import com.dozone.wehagopro.repository.mybatis.MyBatisItemRepository;
import com.dozone.wehagopro.repository.mybatis.UserMapper;
import com.dozone.wehagopro.repository.signUp.SignUpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoginService1 {

    @Autowired
    Loginrepository loginrepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    MyBatisItemRepository itemRepository;
    @Autowired
    private  final PasswordEncoder passwordEncoder;
    @Autowired
    SignUpRepository repository;



    public Integer findEmployeeStateCheck(int empNo){
        System.out.println("employeeStateCheck = " + empNo);
        return loginrepository.selectEmployeeStateCheck(empNo);
    }

    public ShortLinkLoginDto findShortLinkDeadLine(int empNo){
        System.out.println("shortLinkDeadLine = " + empNo);
        return loginrepository.selectShortLinkDeadLine(empNo);
    }

    public void findUserNoPw(Loginupdatedto dto){
        UserDto userDto = userMapper.selectByUserId(dto.getT_user_id())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디 입니다."));
        System.out.println("서비스매소드 실행"+dto.getT_user_id());
        UserNoPwDto noPwDto = itemRepository.selectUserNoPw(dto.getT_user_id());
        System.out.println("아이디로 유저넘버를 select 한 값 : " + noPwDto);
        if (!passwordEncoder.matches(dto.getT_user_password(), noPwDto.getT_user_password())){
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
        itemRepository.updateUserNo(noPwDto.getT_user_no(), dto.getT_employee_no());
        System.out.println("찾은 유저넘버로 employee num을 업데이트 실행");

        List<Integer> serviceNo = repository.selectServiceNoByServiceFree();
        for (int num:
                serviceNo) {
            System.out.println("num = " + num);
            repository.insertServicePublished(dto.getT_employee_no(), num);
        }
    }

    public void insertNotice(NoticeDto noticeDto){
        System.out.println("noticeDto = " + noticeDto);
        itemRepository.insertNotice(noticeDto.getT_user_name(), noticeDto.getT_company_no(), noticeDto.getT_notice_title(), noticeDto.getT_notice_content());
    }

    public List<NoticeSelectDto> selectNotice(int t_company_no){
        List<NoticeSelectDto> dto = itemRepository.selectNotice(t_company_no);
        System.out.println("dto = " + dto);
        return dto;
    }

    public void updateNotice(NoticeSelectDto noticeSelectDto){
        System.out.println("noticeSelectDto = " + noticeSelectDto);
        itemRepository.updateNotice(noticeSelectDto.getT_notice_title(), noticeSelectDto.getT_notice_content(), noticeSelectDto.getT_notice_no());
    }

    public void deleteNotice(NoticeSelectDto noticeSelectDto){
        System.out.println("noticeSelectDto = " + noticeSelectDto);
        itemRepository.deleteNotice(noticeSelectDto.getT_notice_no());
    }

    public void deleteUser(WithdrawalDto withdrawalDto){
        System.out.println("withrawalDto = " + withdrawalDto);
        itemRepository.deleteUser(withdrawalDto.getT_user_no(),withdrawalDto.getT_user_delete_reason());
    }
}
