package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.signUp.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SignUpMapper {

    String selectIdCheck(String id);
    String selectEmailCheck(String email);
    String selectPhoneNumberCheck(String phoneNumber);
    void insertUser(UserSignUpDto dto);
    void insertCompany(CompanySignUpDto dto);
    void insertOrganization(OrganizationSignUpDto dto);
    void insertEmployee(EmployeeSignUpDto dto);
    List<Integer> selectServiceNoByPackageNo(int no);
    List<Integer> selectServiceNoByServiceFree();
    void insertServicePublished(int param1, int param2);
    String selectCompanyCheck(String companyName);
    ShortLinkSignUpDto selectShortLinkByEmpNo(int empNo);
    Integer selectEmployeeStateCheck(int empNo);
    Integer selectUserNoByEmployeeNo(SignUpInviteUpdateDto dto);
    void updateEmployeeStateTo2(SignUpInviteUpdateDto dto);
    void updateInvitedUser(SignUpInviteUpdateDto dto);
    List<CompanyServiceListDto> selectCompanyServiceList(int comNo);
    Integer selectPackageCountByCompanyNo(int comNo);
    CountPublishedServiceAndEmpNoDto selectEachCompanyPublishedCount(int param1, int param2);
    List<UnPublishedUserDto> selectUnPublishedUser(int param1, int param2);
    List<UnPublishedUserDto> selectPublishedUser(int param1, int param2);
    void updateUnPublish(int param1, int param2);
    Integer selectPublishedCount(int param1, int param2);
}
