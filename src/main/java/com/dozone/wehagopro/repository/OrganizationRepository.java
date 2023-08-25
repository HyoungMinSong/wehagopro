package com.dozone.wehagopro.repository;

import com.dozone.wehagopro.domain.*;
import com.dozone.wehagopro.repository.mybatis.OrganizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrganizationRepository {

    private final OrganizationMapper organizationMapper;

    // 직원 목록 유저,직원,부서이름 from 회사번호
    public List<OrganizationUserEmplDto> selectEmployeeList(Integer t_company_no){
        return organizationMapper.selectEmployeeList(t_company_no);
    };
    // 부서 목록 from 회사번호
    public List<OrganizationDto> selectOrganizationList(Integer t_company_no){
        return organizationMapper.selectOrganizationList(t_company_no);
    };
    // 조직도 부서 생성
    public void insertOrganization(String t_organization_name, Integer t_organization_parent){
        organizationMapper.insertOrganization(t_organization_name,t_organization_parent);
    };
    // 조직도 부서 수정
    public void updateOrganization(String t_organization_name, Integer t_organization_no){
        organizationMapper.updateOrganization(t_organization_name, t_organization_no);
    };
    // 조직도 부서 삭제
    public void deleteOrganization(Integer t_organization_no){
        organizationMapper.deleteOrganization(t_organization_no);
    };
    // 임시 회원 등록
    public Integer insertUser(OrganizationEmplRegiDTO dto){
        return organizationMapper.insertUser(dto);
    }
    // 회사 이름인 부서 번호 찾기
    public Integer selectOrganizationNo(String t_organization_name){
        return organizationMapper.selectOrganizationNo(t_organization_name);
    };
    // 직원 등록
    public Integer insertEmployee(OrganizationEmplRegiDTO dto){
        return organizationMapper.insertEmployee(dto);
    };
    // 메일 등록
    public void insertShortlink(OrganizationEmplRegiDTO dto){
        organizationMapper.insertShortlink(dto);
    }
    // 회사 이름 조회
    public String selectCompanyName(Integer t_company_no){
        return organizationMapper.selectCompanyName(t_company_no);
    };
    // 메일 받은 직원 상태 갱신
    public void updateReceivedMailEmployee(Integer t_employee_no){
        organizationMapper.updateReceivedMailEmployee(t_employee_no);
    }
    // 메일 기한 연장
    public void updateReceivedMailShortlink(Integer t_employee_no){
        organizationMapper.updateReceivedMailShortlink(t_employee_no);
    }
    // 유저 사진 수정
    public void updateDetailUser(OrganizationEmplRegiDTO dto){
        organizationMapper.updateDetailUser(dto);
    };
    // 직원 수정
    public void updateDetailEmployee(OrganizationEmplRegiDTO dto){
        organizationMapper.updateDetailEmployee(dto);
    };
    // 직원 상태 수정
    public void updateEmployeeState(Integer t_employee_state, Integer t_employee_no){
      organizationMapper.updateEmployeeState(t_employee_state, t_employee_no);
    };
    // 퇴사시 서비스 배포해제
    public void updateFiredPublish(Integer t_employee_no){
        organizationMapper.updateFiredPublish(t_employee_no);
    }


}
