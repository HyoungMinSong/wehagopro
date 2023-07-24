package com.dozone.wehagopro.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class OrganizationEmplInfoDTO {

    private Integer t_user_no;
    private String t_user_id;
    private String t_user_password;
    private String t_user_name;
    private String t_user_phone;
    private String t_user_email;
    private String t_user_photo_name;
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

    public OrganizationEmplInfoDTO() {
    }

    public OrganizationEmplInfoDTO(String t_user_id, String t_user_password, String t_user_name, String t_user_phone, String t_user_email, String t_user_photo_name, String t_user_photo_path, Date t_user_sign_date, Date t_user_delete_date, Integer t_user_state, Date t_user_update_date, Integer t_company_no, Integer t_employee_auth, Date t_employee_date, Integer t_employee_state, Date t_employee_delete_date, String t_employee_duty, String t_employee_position, Integer t_organization_no, Date t_employee_update_date, String t_organization_name) {
        this.t_user_id = t_user_id;
        this.t_user_password = t_user_password;
        this.t_user_name = t_user_name;
        this.t_user_phone = t_user_phone;
        this.t_user_email = t_user_email;
        this.t_user_photo_name = t_user_photo_name;
        this.t_user_photo_path = t_user_photo_path;
        this.t_user_sign_date = t_user_sign_date;
        this.t_user_delete_date = t_user_delete_date;
        this.t_user_state = t_user_state;
        this.t_user_update_date = t_user_update_date;
        this.t_company_no = t_company_no;
        this.t_employee_auth = t_employee_auth;
        this.t_employee_date = t_employee_date;
        this.t_employee_state = t_employee_state;
        this.t_employee_delete_date = t_employee_delete_date;
        this.t_employee_duty = t_employee_duty;
        this.t_employee_position = t_employee_position;
        this.t_organization_no = t_organization_no;
        this.t_employee_update_date = t_employee_update_date;
        this.t_organization_name = t_organization_name;
    }
}
