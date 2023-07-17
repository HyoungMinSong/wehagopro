package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.domain.MemberSignUpRequestDto;
import com.dozone.wehagopro.domain.TokenDto;
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

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public String join(@RequestBody MemberSignUpRequestDto request) throws Exception {
        return memberService.signUp(request);
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody Map<String, String> member) {
        return memberService.login(member);
    }

    @PostMapping("/logout")
    public String logout(@RequestBody TokenDto tokenDto) {
        return memberService.logout(tokenDto.getAccessToken(), tokenDto.getRefreshToken());
    }

    @PostMapping("/reissue")
    public TokenDto reissue(@RequestBody TokenDto tokenRequestDto) {
        return memberService.reissueAccessToken(tokenRequestDto);
    }
}