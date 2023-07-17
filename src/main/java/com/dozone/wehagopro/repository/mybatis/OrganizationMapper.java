package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrganizationMapper {
    
    // 조직도 회사 이름
    String showMyCompany(Integer t_user_no);
    // 조직도 부서 목록
    List<String> showMyOrganization(Integer t_user_no);
    // 조직도 직원 목록 (회사 선택)
    List<User> showMyEmployeeFromCompany(@Param("t_company_name") String t_company_name, @Param("t_employee_state") Integer t_employee_state);
    // 조직도 직원 목록 (부서 선택)
    List<User> showMyEmployeeFromOrganization(@Param("t_organization_name") String t_organization_name, @Param("t_employee_state") Integer t_employee_state);
}
