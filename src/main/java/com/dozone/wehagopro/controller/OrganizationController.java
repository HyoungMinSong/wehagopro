package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    // 조직도 회사 이름
    @GetMapping("/showMyCompany")
    public String showMyCompany(Integer t_user_no){
        System.out.println(t_user_no);
        return organizationService.showMyCompany(t_user_no);
    }
    // 조직도 부서 목록
    @GetMapping("/showMyOrganization")
    public List<String> showMyOrganization(Integer t_user_no){
        return organizationService.showMyOrganization(t_user_no);
    }

}
