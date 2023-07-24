package com.dozone.wehagopro.domain.signUp;

import lombok.Data;

@Data
public class EmployeeSignUpDto {
    private int no;
    private int userNo;
    private int companyNo;
    private int employeeAuth;
    private int organizationNo;

    public EmployeeSignUpDto(int userNo, int companyNo, int employeeAuth, int organizationNo) {
        this.userNo = userNo;
        this.companyNo = companyNo;
        this.employeeAuth = employeeAuth;
        this.organizationNo = organizationNo;
    }
}
