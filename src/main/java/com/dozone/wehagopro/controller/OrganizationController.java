package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.domain.User;
import com.dozone.wehagopro.service.OrganizationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        String cName =organizationService.showMyCompany(t_user_no) ;
        System.out.println("why? "+cName);
        return cName;
    }
    // 조직도 부서 목록
    @GetMapping("/showMyOrganization")
    public List<String> showMyOrganization(Integer t_user_no){
        return organizationService.showMyOrganization(t_user_no);
    }

    @GetMapping("/showMyEmployees")
    public List<User> showMyEmployees(@RequestParam("nodeName") String nodeName, @RequestParam("index") Integer index, @RequestParam("t_employee_state") Integer t_employee_state){
        System.out.println("nodeName : "+nodeName + ", index : "+index + ", t_employee_state: " + t_employee_state);
        return organizationService.showMyEmployees(nodeName, index, t_employee_state);
    }

}
