package com.dozone.wehagopro.service;

import com.dozone.wehagopro.domain.*;
import com.dozone.wehagopro.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    // 조직도 목록
    public List<OrganizationInitCompDTO> showMyWorkPlace(Integer t_user_no) {
        return organizationRepository.showMyWorkPlace(t_user_no);
    }

    // 조직도 회사 정보
    public List<OrganizationCompInfoDTO> showMyCompanyInfo(Integer t_user_no){
        return organizationRepository.showMyCompanyInfo(t_user_no);
    }

    // 조직도 직원 상태
    public OrganizationInitEmplDTO showMyEmployeeState(Integer t_company_no) {
        return organizationRepository.showMyEmployeeState(t_company_no);
    }

    // 조직도 직원 목록 (회사 or 부서 선택)
    public List<UserDTO> showMyEmployees(String nodeName, Integer pk, Integer index, Integer t_employee_state) {
        if (index == -1) {
            System.out.println("회사야");
            return organizationRepository.showMyEmployeeFromCompany(nodeName, pk, t_employee_state);
        } else {
            System.out.println("부서야");
            return organizationRepository.showMyEmployeeFromOrganization(nodeName, pk, t_employee_state);
        }
    }

    // 조직도 부서 수정
    public void editingOrganization(List<OrganizationEditDTO> insertDto, List<OrganizationEditDTO> updateDto, List<OrganizationEditDTO> deleteDto) {
        // 조직도 부서 추가
        for (OrganizationEditDTO dto : insertDto) {
            organizationRepository.createOrganization(dto.getT_organization_name(), dto.getT_company_no());
        }
        // 조직도 부서 수정
        for (OrganizationEditDTO dto : updateDto) {
            organizationRepository.updateOrganization(dto.getT_organization_name(), dto.getT_organization_no());
        }
        // 조직도 부서 삭제
        for (OrganizationEditDTO dto : deleteDto) {
            organizationRepository.deleteOrganization(dto.getT_organization_no());
        }
    }

    ;

}
