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

    // 조직도 목록
    public List<OrganizationInitCompDTO> showMyWorkPlace(Integer t_user_no){
        return organizationMapper.showMyWorkPlace(t_user_no);
    }

    // 조직도 회사 정보
    public List<OrganizationCompInfoDTO> showMyCompanyInfo(Integer t_user_no){
        return organizationMapper.showMyCompanyInfo(t_user_no);
    }

    // 조직도 직원 상태 회사
    public OrganizationInitEmplDTO showMyEmployeeStateFromCompany(Integer t_company_no){
        return organizationMapper.showMyEmployeeStateFromCompany(t_company_no);
    }
    //조직도 직원 상태 부서
    public OrganizationInitEmplDTO showMyEmployeeStateFromOrganization(Integer t_organization_no){
        return organizationMapper.showMyEmployeeStateFromOrganization(t_organization_no);
    }

    // 조직도 직원 목록 (회사 선택)
    public List<OrganizationEmplInfoDTO> showMyEmployeeFromCompany(String t_company_name, Integer pk, Integer t_employee_state){
        return organizationMapper.showMyEmployeeFromCompany(t_company_name, pk, t_employee_state);
    }

    // 조직도 직원 목록 (회사 선택)
    public List<OrganizationEmplInfoDTO> showMyEmployeeFromOrganization(String t_organization_name, Integer pk, Integer t_employee_state){
        return organizationMapper.showMyEmployeeFromOrganization(t_organization_name, pk, t_employee_state);
    }

    // 조직도 부서 생성
    public void createOrganization(String t_organization_name, Integer t_company_no){
        organizationMapper.createOrganization(t_organization_name,t_company_no);
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
    public Integer registerUser(OrganizationEmplRegiDTO dto){
        return organizationMapper.registerUser(dto);
    }
    // 직원 등록
    public Integer registerEmployee(OrganizationEmplRegiDTO dto){
        return organizationMapper.registerEmployee(dto);
    };
    // 메일 등록
    public void createShortLink(OrganizationEmplRegiDTO dto){
        organizationMapper.createShortLink(dto);
    }

}
