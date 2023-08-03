package com.dozone.wehagopro.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCompanyDto {
    private Integer t_company_no;
    private String t_company_name;
    private String t_company_clasification;
    private String t_company_business;
    private String t_company_sectors;
    private String t_company_representative;
    private String t_company_call_num;
    private String t_employee_auth;
    private Date t_employee_date;
    private String t_employee_duty;
    private String t_employee_position;
}