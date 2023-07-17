package com.dozone.wehagopro.config;

import com.dozone.wehagopro.domain.Member;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {
    private static final long serialVersionUID = 1L;

    public SecurityUser(Member member) {
        // 여기서 UserDetails 객체 생성
        super(member.getId(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
    }
}