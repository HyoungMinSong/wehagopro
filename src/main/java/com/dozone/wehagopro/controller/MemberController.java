package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.domain.MemberSignUpRequestDto;
import com.dozone.wehagopro.repository.MemberMapper;
import com.dozone.wehagopro.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public String join(@RequestBody MemberSignUpRequestDto request) throws Exception {
        return memberService.signUp(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> member) {
        return memberService.login(member);
    }

    @PostMapping("/logout")
    public String logout() {
        return null;
    }
}