package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.domain.OrganizationEditDTO;
import com.dozone.wehagopro.domain.OrganizationInitEmplDTO;
import com.dozone.wehagopro.domain.UserDTO;
import com.dozone.wehagopro.domain.OrganizationInitCompDTO;
import com.dozone.wehagopro.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    // 조직도 목록
    @GetMapping("/showMyWorkPlace")
    public List<OrganizationInitCompDTO> showMyWorkPlace(Integer t_user_no){
        return organizationService.showMyWorkPlace(t_user_no);
    }

    // 조직도 직원 상태
    @GetMapping("/showMyEmployeeState")
    public OrganizationInitEmplDTO showMyEmployeeState(Integer t_company_no){
        return organizationService.showMyEmployeeState(t_company_no);
    }

    // 조직도 직원 목록
    @GetMapping("/showMyEmployees")
    public List<UserDTO> showMyEmployees(@RequestParam("nodeName") String nodeName, @RequestParam("pk") Integer pk, @RequestParam("index") Integer index, @RequestParam("t_employee_state") Integer t_employee_state){
        return organizationService.showMyEmployees(nodeName, pk, index, t_employee_state);
    }

    // 조직도 부서 수정
    @PostMapping("/editingOrganization")
    public void editingOrganization(@RequestBody List<OrganizationEditDTO> dto){
        System.out.println(dto.get(0));
        System.out.println(dto.get(0).getT_organization_name());
        System.out.println(dto.get(0).getT_company_no());
        System.out.println(dto.get(0).getT_organization_no());
    }

}
