package com.dozone.wehagopro.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCompanyDto {
    private String t_company_name;
    private String t_employee_auth;
    private String t_employee_duty;
}