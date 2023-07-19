package com.dozone.wehagopro.service;

import com.dozone.wehagopro.domain.CountEmployee;
import com.dozone.wehagopro.domain.User;
import com.dozone.wehagopro.domain.WorkPlace;
import com.dozone.wehagopro.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    // 조직도 목록
    public List<WorkPlace> showMyWorkPlace(Integer t_user_no){
        return organizationRepository.showMyWorkPlace(t_user_no);
    }

    // 조직도 직원 상태
    public CountEmployee showMyEmployeeState(Integer t_company_no){
        return organizationRepository.showMyEmployeeState(t_company_no);
    }

    // 조직도 직원 목록 (회사 or 부서 선택)
    public List<User> showMyEmployees(String nodeName, Integer pk, Integer index, Integer t_employee_state){
        if(index==-1){
            System.out.println("회사야");
            return organizationRepository.showMyEmployeeFromCompany(nodeName, pk, t_employee_state);
        }else{
            System.out.println("부서야");
            return organizationRepository.showMyEmployeeFromOrganization(nodeName, pk, t_employee_state);
        }
    }

}
