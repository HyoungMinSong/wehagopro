package com.dozone.wehagopro.repository;

import com.dozone.wehagopro.domain.CountEmployee;
import com.dozone.wehagopro.domain.User;
import com.dozone.wehagopro.domain.WorkPlace;
import com.dozone.wehagopro.repository.mybatis.OrganizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrganizationRepository {

    private final OrganizationMapper organizationMapper;

    // 조직도 목록
    public List<WorkPlace> showMyWorkPlace(Integer t_user_no){
        return organizationMapper.showMyWorkPlace(t_user_no);
    }

    // 조직도 직원 상태
    public CountEmployee showMyEmployeeState(Integer t_company_no){
        return organizationMapper.showMyEmployeeState(t_company_no);
    }

    // 조직도 직원 목록 (회사 선택)
    public List<User> showMyEmployeeFromCompany(String t_company_name, Integer pk, Integer t_employee_state){
        return organizationMapper.showMyEmployeeFromCompany(t_company_name, pk, t_employee_state);
    }

    // 조직도 직원 목록 (회사 선택)
    public List<User> showMyEmployeeFromOrganization(String t_organization_name, Integer pk, Integer t_employee_state){
        return organizationMapper.showMyEmployeeFromOrganization(t_organization_name, pk, t_employee_state);
    }
}
