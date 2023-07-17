package com.dozone.wehagopro.domain;

import lombok.Data;

@Data
public class Organization {

    private int t_organization_no;

    private String t_organization_name;

    private int t_organization_parents;

    public Organization() {
    }

    public Organization(String t_organization_name, int t_organization_parents) {
        this.t_organization_name = t_organization_name;
        this.t_organization_parents = t_organization_parents;
    }
}
