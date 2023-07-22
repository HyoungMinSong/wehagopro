package com.dozone.wehagopro.repository;

import com.dozone.wehagopro.domain.OrganizationCompInfoDTO;
import com.dozone.wehagopro.domain.OrganizationInitEmplDTO;
import com.dozone.wehagopro.domain.UserDTO;
import com.dozone.wehagopro.domain.OrganizationInitCompDTO;
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

    // 조직도 직원 상태
    public OrganizationInitEmplDTO showMyEmployeeState(Integer t_company_no){
        return organizationMapper.showMyEmployeeState(t_company_no);
    }

    // 조직도 직원 목록 (회사 선택)
    public List<UserDTO> showMyEmployeeFromCompany(String t_company_name, Integer pk, Integer t_employee_state){
        return organizationMapper.showMyEmployeeFromCompany(t_company_name, pk, t_employee_state);
    }

    // 조직도 직원 목록 (회사 선택)
    public List<UserDTO> showMyEmployeeFromOrganization(String t_organization_name, Integer pk, Integer t_employee_state){
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
}
