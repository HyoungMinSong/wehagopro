package com.dozone.wehagopro.service;

import com.dozone.wehagopro.config.JwtTokenProvider;
import com.dozone.wehagopro.domain.Member;
import com.dozone.wehagopro.domain.MemberSignUpRequestDto;
import com.dozone.wehagopro.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public String signUp(MemberSignUpRequestDto requestDto) throws Exception {

        if (memberMapper.findByMembername(requestDto.getId()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if (!requestDto.getPassword().equals(requestDto.getPassword())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        } // 나중에 checkedPassword로 수정

        Member member = requestDto.toEntity();
        member.encodePassword(passwordEncoder);
        memberMapper.insertMember(member.getId(), member.getPassword(), member.getName(), member.getRole().toString(), member.getEnabled());

        return member.getId();
    }

    @Override
    public String login(Map<String, String> members) {

        Member member = memberMapper.findByMembername(members.get("id"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디 입니다."));

        String password = members.get("password");
        System.out.println(passwordEncoder.encode(password));

        // passwordEncoder.matches(로그인 할 때 비밀번호, DB에 저장된 비밀번호)
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        String role = member.getRole();

        return jwtTokenProvider.createToken(member.getId(), role);
    }

    @Override
    public String logout() {
        return null;
    }
}