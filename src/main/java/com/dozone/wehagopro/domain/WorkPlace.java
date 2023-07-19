package com.dozone.wehagopro.domain;

import lombok.Data;

@Data
public class WorkPlace {

    private Integer t_company_no;

    private String t_company_name;

    private Integer t_organization_no;

    private String t_organization_name;

    public WorkPlace() {
    }

    public WorkPlace(Integer t_company_no, String t_company_name, Integer t_organization_no, String t_organization_name) {
        this.t_company_no = t_company_no;
        this.t_company_name = t_company_name;
        this.t_organization_no = t_organization_no;
        this.t_organization_name = t_organization_name;
    }
}
