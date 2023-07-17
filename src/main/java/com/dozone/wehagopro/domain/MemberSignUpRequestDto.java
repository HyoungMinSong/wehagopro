package com.dozone.wehagopro.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberSignUpRequestDto {
    private String id;
    private String password;
    private String name;
    private String role;
    private char enabled;

    @Builder
    public Member toEntity(){
        return Member.builder()
                .id(id)
                .password(password)
                .name(name)
                .role(role)
                .enabled(enabled)
                .build();
    }
}