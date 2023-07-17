package com.dozone.wehagopro.domain;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {
    private String id;
    private String password;
    private String name;
    private String role;
    private char enabled;

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}