package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.OrganizationCompInfoDTO;
import com.dozone.wehagopro.domain.OrganizationInitEmplDTO;
import com.dozone.wehagopro.domain.UserDTO;
import com.dozone.wehagopro.domain.OrganizationInitCompDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrganizationMapper {

    // 조직도 목록
    List<OrganizationInitCompDTO> showMyWorkPlace(Integer t_user_no);
    // 조직도 회사 목록
    List<OrganizationCompInfoDTO> showMyCompanyInfo(Integer t_user_no);
    // 조직도 직원 상태
    OrganizationInitEmplDTO showMyEmployeeState(Integer t_company_no);
    // 조직도 직원 목록 (회사 선택)
    List<UserDTO> showMyEmployeeFromCompany(@Param("t_company_name") String t_company_name, @Param("t_company_no") Integer pk, @Param("t_employee_state") Integer t_employee_state);
    // 조직도 직원 목록 (부서 선택)
    List<UserDTO> showMyEmployeeFromOrganization(@Param("t_organization_name") String t_organization_name, @Param("t_organization_no") Integer pk, @Param("t_employee_state") Integer t_employee_state);
    // 조직도 부서 생성
    void createOrganization(@Param("t_organization_name") String t_organization_name, @Param("t_company_no") Integer t_company_no);
    // 조직도 부서 수정
    void updateOrganization(@Param("t_organization_name") String t_organization_name, @Param("t_organization_no") Integer t_organization_no);
    // 조직도 부서 삭제
    void deleteOrganization(@Param("t_organization_no") Integer t_organization_no);
}
