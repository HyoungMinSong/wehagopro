package com.dozone.wehagopro.config;

import com.dozone.wehagopro.domain.UserDTO;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class SecurityUser extends User {
    private static final long serialVersionUID = 1L;

    public SecurityUser(UserDTO userDto) {
        // 여기서 UserDetails 객체 생성
        super(userDto.getT_user_id(), userDto.getT_user_password(), Collections.emptyList());
    }
}