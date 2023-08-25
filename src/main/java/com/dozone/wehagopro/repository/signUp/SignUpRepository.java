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

    public String selectIdCheck(String id){
        System.out.println("repsitoryid = " + id);
        return mapper.selectIdCheck(id);
    }
    public String selectEmailCheck(String email){
        System.out.println("email = " + email);
        return mapper.selectEmailCheck(email);
    }

    public String selectPhoneNumberCheck(String phoneNumber){
        System.out.println("phoneNumber = " + phoneNumber);
        return mapper.selectPhoneNumberCheck(phoneNumber);
    }
    public String insertUser(UserSignUpDto dto){
        System.out.println("리포지토리");
        System.out.println("dto = " + dto);
        mapper.insertUser(dto);

        return "리포지토리성공?";
    }

    public String insertCompany(CompanySignUpDto dto){
        System.out.println("리포지토리");
        System.out.println("dto = " + dto);
        mapper.insertCompany(dto);

        return "리포지토리성공?";
    }

    public String insertOrganization(OrganizationSignUpDto dto){
        System.out.println("리포지토리");
        System.out.println("dto = " + dto);
        mapper.insertOrganization(dto);

        return "리포지토리성공?";
    }

    public String insertEmployee(EmployeeSignUpDto dto){
        System.out.println("리포지토리");
        System.out.println("dto = " + dto);
        mapper.insertEmployee(dto);

        return "리포지토리성공?";
    }

    public List<Integer> selectServiceNoByPackageNo(int no){
        System.out.println("리포지토리");
        System.out.println("PackageNo = " + no);
        return mapper.selectServiceNoByPackageNo(no);
    }

    public List<Integer> selectServiceNoByServiceFree(){
        System.out.println("리포지토리");
        System.out.println("freeNo = ");
        return mapper.selectServiceNoByServiceFree();
    }

    public String insertServicePublished(int employeeNo, int serviceNo){
        System.out.println("리포지토리");
        System.out.println("employeeNo = " + employeeNo + "serviceNo = " + serviceNo);
        mapper.insertServicePublished(employeeNo,serviceNo);

        return "리포지토리성공?";
    }

    public String selectCompanyCheck(String companyName) {
        System.out.println("repsitorycompanyName = " + companyName);
        return mapper.selectCompanyCheck(companyName);
    }

    public ShortLinkSignUpDto selectShortLinkByEmpNo(int empNo){
        System.out.println("repsitoryempNo = " + empNo);
        return mapper.selectShortLinkByEmpNo(empNo);
    }

    public Integer selectEmployeeStateCheck(int empNo){
        System.out.println("repsitoryempNo = " + empNo);
        return mapper.selectEmployeeStateCheck(empNo);
    }

    public Integer selectUserNoByEmployeeNo(SignUpInviteUpdateDto dto){
        System.out.println("dto = " + dto);
        return mapper.selectUserNoByEmployeeNo(dto);
    }

    public void updateEmployeeStateTo2(SignUpInviteUpdateDto dto){
        System.out.println("dto = " + dto);
        mapper.updateEmployeeStateTo2(dto);
    }

    public void updateInvitedUser(SignUpInviteUpdateDto dto){
        System.out.println("dto = " + dto);
        mapper.updateInvitedUser(dto);
    }

    public List<CompanyServiceListDto> selectCompanyServiceList(int comNo){
        System.out.println("comNo = " + comNo);
        return mapper.selectCompanyServiceList(comNo);
    }

    public Integer selectPackageCountByCompanyNo(int comNo){
        System.out.println("comNo = " + comNo);
        return mapper.selectPackageCountByCompanyNo(comNo);
    }

    public CountPublishedServiceAndEmpNoDto selectEachCompanyPublishedCount(int serviceNo, int comNo){
        System.out.println("serviceNo = " + serviceNo + ", comNo = " + comNo);
        return mapper.selectEachCompanyPublishedCount(serviceNo,comNo);
    }

    public List<UnPublishedUserDto> selectUnPublishedUser(int comNo, int serviceNo){
        System.out.println("comNo = " + comNo + ", serviceNo = " + serviceNo);
        return mapper.selectUnPublishedUser(comNo, serviceNo);
    }

    public List<UnPublishedUserDto> selectPublishedUser(int comNo, int serviceNo){
        System.out.println("comNo = " + comNo + ", serviceNo = " + serviceNo);
        return mapper.selectPublishedUser(comNo, serviceNo);
    }

    public void updateUnPublish(int empNo, int serviceNo){
        System.out.println("empNo = " + empNo + ", serviceNo = " + serviceNo);
        mapper.updateUnPublish(empNo,serviceNo);
    }

    public Integer selectPublishedCount(int serviceNo, int comNo){
        System.out.println("serviceNo = " + serviceNo + ", comNo = " + comNo);
        return mapper.selectPublishedCount(serviceNo,comNo);
    }
}
