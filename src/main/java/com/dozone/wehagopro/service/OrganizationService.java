package com.dozone.wehagopro.service;

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

}
