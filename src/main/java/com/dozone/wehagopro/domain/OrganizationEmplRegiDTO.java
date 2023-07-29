package com.dozone.wehagopro.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class OrganizationEmplRegiDTO {

    private Integer t_company_no;
    private Integer t_employee_auth;
    private Date t_employee_date;
    private String t_employee_duty;
    private String t_employee_position;
    private Integer t_organization_no;

    private Integer t_employee_no;

    private Integer t_user_no;
    private String t_user_name;
    private String t_user_phone;
    private String t_user_photo_path;
    private String t_user_photo_name;
    private String t_user_email;
    private String t_shortlink_link;

}
