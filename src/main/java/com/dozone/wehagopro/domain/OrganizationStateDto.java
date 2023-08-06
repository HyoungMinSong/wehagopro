package com.dozone.wehagopro.domain;

import lombok.Data;

import java.util.List;

@Data
public class OrganizationStateDto {

    private Integer t_employee_state;

    private List<OrganizationSelectedDto> checkedEmployee;
}
