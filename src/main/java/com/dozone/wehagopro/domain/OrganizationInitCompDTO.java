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

    private Integer company_organization_no;


}
