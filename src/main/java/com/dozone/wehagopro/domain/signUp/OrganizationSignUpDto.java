package com.dozone.wehagopro.domain.signUp;

import lombok.Data;

@Data
public class OrganizationSignUpDto {
    private int no;
    private String companyName;

    public OrganizationSignUpDto(String companyName) {
        this.companyName = companyName;
    }
}
