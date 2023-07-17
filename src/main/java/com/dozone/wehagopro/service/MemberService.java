package com.dozone.wehagopro.service;

import com.dozone.wehagopro.domain.MemberSignUpRequestDto;

import java.util.Map;

public interface MemberService {
    public String signUp(MemberSignUpRequestDto requestDto) throws Exception;
    public String login(Map<String, String> members);
    public String logout();
}