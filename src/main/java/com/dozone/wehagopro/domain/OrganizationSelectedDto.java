package com.dozone.wehagopro.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class OrganizationSelectedDto {

    private String t_employee_duty;
    private Integer t_employee_no;
    private String t_employee_position;
    private String t_organization_name;
    private String t_user_name;
    private Integer t_user_no;
    private String t_company_name;

    private Integer t_company_no;
    private String t_user_email;
}
