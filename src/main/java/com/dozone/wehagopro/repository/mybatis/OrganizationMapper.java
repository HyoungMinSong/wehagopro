package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.*;
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
    OrganizationInitEmplDTO showMyEmployeeStateFromCompany(Integer t_company_no);
    // 조직도 직원 상태
    OrganizationInitEmplDTO showMyEmployeeStateFromOrganization(Integer t_organization_no);
    // 조직도 직원 목록 (회사 선택)
    List<OrganizationEmplInfoDTO> showMyEmployeeFromCompany(@Param("t_company_name") String t_company_name, @Param("t_company_no") Integer pk, @Param("t_employee_state") Integer t_employee_state);
    // 조직도 직원 목록 (부서 선택)
    List<OrganizationEmplInfoDTO> showMyEmployeeFromOrganization(@Param("t_organization_name") String t_organization_name, @Param("t_organization_no") Integer pk, @Param("t_employee_state") Integer t_employee_state);
    // 조직도 부서 생성
    void createOrganization(@Param("t_organization_name") String t_organization_name, @Param("t_company_no") Integer t_company_no);
    // 조직도 부서 수정
    void updateOrganization(@Param("t_organization_name") String t_organization_name, @Param("t_organization_no") Integer t_organization_no);
    // 조직도 부서 삭제
    void deleteOrganization(@Param("t_organization_no") Integer t_organization_no);
    // 임시 회원 등록
    int registerUser(OrganizationEmplRegiDTO dto);
    // 직원 등록
    int registerEmployee(OrganizationEmplRegiDTO dto);
    // 메일 등록
    void createShortLink(OrganizationEmplRegiDTO dto);
    // 회사 이름 조회
    String findCompanyNameFromCompanyNo(Integer t_company_no);
    // 메일 받은 직원 상태 변경
    void updateReceivedMailEmployee(Integer t_employee_no);
    // 메일 기한 연장
    void updateReceivedMailShortlink(Integer t_employee_no);
    // 유저 사진 수정
    void updateDetailUser(OrganizationEmplRegiDTO dto);
    // 직원 수정
    void updateDetailEmployee(OrganizationEmplRegiDTO dto);

}
