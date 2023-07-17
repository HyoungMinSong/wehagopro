package com.dozone.wehagopro.service;

import com.dozone.wehagopro.config.SecurityUser;
import com.dozone.wehagopro.domain.Member;
import com.dozone.wehagopro.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optional = memberMapper.findByMembername(username);
        if(!optional.isPresent()) {
            throw new UsernameNotFoundException("사용자가 없습니다.");
        } else {
            Member member = optional.get();
            return new SecurityUser(member);
        }
    }

}