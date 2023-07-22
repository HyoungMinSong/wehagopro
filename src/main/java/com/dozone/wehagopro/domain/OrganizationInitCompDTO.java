package com.dozone.wehagopro.domain;

import lombok.Data;

@Data
public class OrganizationInitCompDTO {

    private Integer t_company_no;

    private String t_company_name;

    private Integer t_organization_no;

    private String t_organization_name;

    private Integer company_employee_count;

    private Integer organization_employee_count;

    private Integer row_index;

    public OrganizationInitCompDTO() {
    }

    public OrganizationInitCompDTO(Integer t_company_no, String t_company_name, Integer t_organization_no, String t_organization_name, Integer company_employee_count, Integer organization_employee_count, Integer row_index) {
        this.t_company_no = t_company_no;
        this.t_company_name = t_company_name;
        this.t_organization_no = t_organization_no;
        this.t_organization_name = t_organization_name;
        this.company_employee_count = company_employee_count;
        this.organization_employee_count = organization_employee_count;
        this.row_index = row_index;
    }
}
