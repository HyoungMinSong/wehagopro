package com.dozone.wehagopro.domain;

import lombok.Data;

@Data
public class OrganizationEditDTO {

    private String t_organization_name;

    private Integer t_company_no;

    private Integer t_organization_no;

    public OrganizationEditDTO() {
    }

    public OrganizationEditDTO(String t_organization_name, Integer t_company_no, Integer t_organization_no) {
        this.t_organization_name = t_organization_name;
        this.t_company_no = t_company_no;
        this.t_organization_no = t_organization_no;
    }
}
