package com.dozone.wehagopro.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class OrganizationUserEmplDto {
    private Integer t_user_no;
    private String t_user_id;
    private String t_user_password;
    private String t_user_name;
    private String t_user_phone;
    private String t_user_email;
    private String t_user_photo_path;
    private Date t_user_sign_date;
    private Date t_user_delete_date;
    private Integer t_user_state;
    private Date t_user_update_date;
    private Integer t_employee_no;
    private Integer t_company_no;
    private Integer t_employee_auth;
    private Date t_employee_date;
    private Integer t_employee_state;
    private Date t_employee_delete_date;
    private String t_employee_duty;
    private String t_employee_position;
    private Integer t_organization_no;
    private Date t_employee_update_date;
    private String t_organization_name;


}
