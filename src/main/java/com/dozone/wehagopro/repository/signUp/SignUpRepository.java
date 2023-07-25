package com.dozone.wehagopro.repository.signUp;

import com.dozone.wehagopro.domain.signUp.*;
import com.dozone.wehagopro.repository.mybatis.SignUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SignUpRepository {

    @Autowired
    SignUpMapper mapper;

    public String idCheck(String id){
        System.out.println("repsitoryid = " + id);
        return mapper.idCheck(id);
    }

    public String signUpUser(UserSignUpDto dto){
        System.out.println("리포지토리");
        System.out.println("dto = " + dto);
        mapper.userSave(dto);

        return "리포지토리성공?";
    }

    public String signUpCompany(CompanySignUpDto dto){
        System.out.println("리포지토리");
        System.out.println("dto = " + dto);
        mapper.companySave(dto);

        return "리포지토리성공?";
    }

    public String signUpOrganization(OrganizationSignUpDto dto){
        System.out.println("리포지토리");
        System.out.println("dto = " + dto);
        mapper.organizationSave(dto);

        return "리포지토리성공?";
    }

    public String signUpEmployee(EmployeeSignUpDto dto){
        System.out.println("리포지토리");
        System.out.println("dto = " + dto);
        mapper.employeeSave(dto);

        return "리포지토리성공?";
    }

    public List<Integer> findServiceNoByPackageNo(int no){
        System.out.println("리포지토리");
        System.out.println("PackageNo = " + no);
        return mapper.findServiceNoByPackageNo(no);
    }

    public List<Integer> findServiceNoByServiceFree(){
        System.out.println("리포지토리");
        System.out.println("freeNo = ");
        return mapper.findServiceNoByServiceFree();
    }

    public String signUpServicePublished(int employeeNo, int serviceNo){
        System.out.println("리포지토리");
        System.out.println("employeeNo = " + employeeNo + "serviceNo = " + serviceNo);
        mapper.servicePublishedSave(employeeNo,serviceNo);

        return "리포지토리성공?";
    }
}