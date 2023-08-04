package com.dozone.wehagopro.domain;

import lombok.Data;

@Data
public class OrganizationDto {
    private Integer t_organization_no;
    private String t_organization_name;
    private Integer t_organization_parent;

    private Integer rownum;
}
