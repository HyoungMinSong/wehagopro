package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrganizationMapper {

    // 직원 목록 유저,직원,부서이름 from 회사번호
    List<OrganizationUserEmplDto> selectEmployeeList(Integer t_company_no);
    // 부서 목록 from 회사번호
    List<OrganizationDto> selectOrganizationList(Integer t_company_no);
    // 조직도 부서 생성
    void insertOrganization(@Param("t_organization_name") String t_organization_name, @Param("t_organization_parent") Integer t_organization_parent);
    // 조직도 부서 수정
    void updateOrganization(@Param("t_organization_name") String t_organization_name, @Param("t_organization_no") Integer t_organization_no);
    // 조직도 부서 삭제
    void deleteOrganization(Integer t_organization_no);
    // 임시 회원 등록
    int insertUser(OrganizationEmplRegiDTO dto);
    // 회사 이름의 부서 번호 찾아오기
    int selectOrganizationNo(String t_organization_name);
    // 직원 등록
    int insertEmployee(OrganizationEmplRegiDTO dto);
    // 메일 등록
    void insertShortlink(OrganizationEmplRegiDTO dto);
    // 회사 이름 조회
    String selectCompanyName(Integer t_company_no);
    // 메일 받은 직원 상태 변경
    void updateReceivedMailEmployee(Integer t_employee_no);
    // 메일 기한 연장
    void updateReceivedMailShortlink(Integer t_employee_no);
    // 유저 사진 수정
    void updateDetailUser(OrganizationEmplRegiDTO dto);
    // 직원 수정
    void updateDetailEmployee(OrganizationEmplRegiDTO dto);
    // 직원 상태 수정
    void updateEmployeeState(@Param("t_employee_state") Integer t_employee_state, @Param("t_employee_no") Integer t_employee_no);
    // 퇴사시 배포서비스 해제
    void updateFiredPublish(Integer t_employee_no);



}
