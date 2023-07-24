package com.dozone.wehagopro.domain;

import lombok.Data;

@Data
public class OrganizationCompInfoDTO {

    private Integer t_company_no;

    private String t_company_name;

    private Integer company_employee_count;
    public OrganizationCompInfoDTO() {
    }

    public OrganizationCompInfoDTO(Integer t_company_no, String t_company_name, Integer company_employee_count) {
        this.t_company_no = t_company_no;
        this.t_company_name = t_company_name;
        this.company_employee_count = company_employee_count;
    }
}
