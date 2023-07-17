package com.dozone.wehagopro.repository;

import com.dozone.wehagopro.domain.User;
import com.dozone.wehagopro.repository.mybatis.OrganizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrganizationRepository {

    private final OrganizationMapper organizationMapper;
    
    // 조직도 회사 이름
    public String showMyCompany(Integer t_user_no){
        return organizationMapper.showMyCompany(t_user_no);
    }

    // 조직도 부서 목록
    public List<String> showMyOrganization(Integer t_user_no){
        return organizationMapper.showMyOrganization(t_user_no);
    }

    // 조직도 직원 목록 (회사 선택)
    public List<User> showMyEmployeeFromCompany(String t_company_name, Integer t_employee_state){
        return organizationMapper.showMyEmployeeFromCompany(t_company_name,t_employee_state);
    }

    // 조직도 직원 목록 (회사 선택)
    public List<User> showMyEmployeeFromOrganization(String t_organization_name, Integer t_employee_state){
        return organizationMapper.showMyEmployeeFromOrganization(t_organization_name,t_employee_state);
    }
}
