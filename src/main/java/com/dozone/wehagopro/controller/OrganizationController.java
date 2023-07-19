package com.dozone.wehagopro.controller;

import com.dozone.wehagopro.domain.CountEmployee;
import com.dozone.wehagopro.domain.User;
import com.dozone.wehagopro.domain.WorkPlace;
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

    // 조직도 목록
    @GetMapping("/showMyWorkPlace")
    public List<WorkPlace> showMyWorkPlace(Integer t_user_no){
        return organizationService.showMyWorkPlace(t_user_no);
    }

    // 조직도 직원 상태
    @GetMapping("/showMyEmployeeState")
    public CountEmployee showMyEmployeeState(Integer t_company_no){
        return organizationService.showMyEmployeeState(t_company_no);
    }

    // 조직도 직원 목록
    @GetMapping("/showMyEmployees")
    public List<User> showMyEmployees(@RequestParam("nodeName") String nodeName, @RequestParam("pk") Integer pk, @RequestParam("index") Integer index, @RequestParam("t_employee_state") Integer t_employee_state){
        return organizationService.showMyEmployees(nodeName, pk, index, t_employee_state);
    }

}
