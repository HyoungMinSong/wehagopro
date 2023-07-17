package com.dozone.wehagopro.service;

import com.dozone.wehagopro.domain.User;
import com.dozone.wehagopro.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    // 조직도 회사 이름
    public String showMyCompany(Integer t_user_no){
        return organizationRepository.showMyCompany(t_user_no);
    }
    // 조직도 부서 목록
    public List<String> showMyOrganization(Integer t_user_no){
        return organizationRepository.showMyOrganization(t_user_no);
    }

    // 조직도 직원 목록 (회사 or 부서 선택)
    public List<User> showMyEmployees(String nodeName, Integer index, Integer t_employee_state){
        if(index==-1){
            System.out.println("회사야");
            return organizationRepository.showMyEmployeeFromCompany(nodeName,t_employee_state);
        }else{
            System.out.println("부서야");
            return organizationRepository.showMyEmployeeFromOrganization(nodeName,t_employee_state);
        }
    }

}
