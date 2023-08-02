package com.dozone.wehagopro.domain;

import lombok.Data;

import java.util.List;

@Data
public class OrganizationMailDto {
    private String employer;
    private List<OrganizationSelectedDto> checkedEmployee;

}
